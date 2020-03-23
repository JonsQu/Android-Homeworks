package fi.shadow.mylocationweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private final int PERMISSION_REQUEST_ID = 10;
    private LocationManager locationManager;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private LocListener locListener;
    private TextView weather;
    private ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main);
        weather = findViewById(R.id.weather);
        weatherIcon = findViewById(R.id.weatherIcon);
        mapView = findViewById(R.id.myMap);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                MainActivity.this.mapboxMap = mapboxMap;
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        int persmissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.ACCESS_FINE_LOCATION);

                        if(persmissionCheck == PackageManager.PERMISSION_DENIED){
                            String [] listOfPersmissions = {Manifest.permission.ACCESS_FINE_LOCATION};

                            ActivityCompat.requestPermissions(MainActivity.this,
                                    listOfPersmissions,
                                    PERMISSION_REQUEST_ID);
                        }else {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                    0, 0, locListener);
                        }
                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });

            }
        });
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locListener = new LocListener();

    }

    public void getLocation(View view) {

    }
    private class LoadWeatherData extends AsyncTask<URL, Integer, InputStream> {
        protected InputStream doInBackground(URL... urls) {
            HttpURLConnection urlConnection = null;
            Log.d("MainActivity", String.valueOf(urls[0]));
            InputStream in = null;
            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
                Log.d("MainActivity", String.valueOf(in));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return in;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(InputStream result) {
            JsonObject json;
            JsonElement element = new JsonParser().parse(
                    new InputStreamReader(result)
            );
            json = element.getAsJsonObject();
            Log.d("MainActivity", String.valueOf(json));
            JsonObject main = (JsonObject) json.get("main");
            JsonElement weathers = json.getAsJsonArray("weather").get(0);
            String icon = String.valueOf(weathers.getAsJsonObject().get("icon").getAsString());
            String temp = String.valueOf(main.get("temp"));
            URL url = null;
            try {
                url =  new URL("https://openweathermap.org/img/wn/" + icon + "@2x.png");
                Log.d("MainActivity", icon);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            new LoadWeatherIcon().execute(url);
            weather.setText(temp + "C");
            Toast.makeText(MainActivity.this, "Current location temp: " + temp, Toast.LENGTH_LONG).show();
        }
    }
    private class LoadWeatherIcon extends AsyncTask<URL, Integer, InputStream>{

        @Override
        protected InputStream doInBackground(URL... urls) {
            HttpURLConnection urlConnection = null;
            InputStream in = null;
            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return in;
        }
        protected void onPostExecute(InputStream result) {
            Bitmap ic = BitmapFactory.decodeStream(result);
            Drawable d = new BitmapDrawable(getResources(), ic);
            Log.d("MainActivity", d.toString());
            weatherIcon.setImageDrawable(d);
            Log.d("MainActivity", String.valueOf(result));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grandResults){
        if(requestCode == PERMISSION_REQUEST_ID){
            if(grandResults.length > 0 && grandResults[0] == PackageManager.PERMISSION_GRANTED){
                try {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            0, 0, locListener);
                }catch (SecurityException e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(this, "Location Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    class LocListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            Log.d("MainActivity", "latitude: " + location.getLatitude() +
                    " - longitude: " + location.getLongitude());
            URL url = null;
            try {
                url =  new URL("https://api.openweathermap.org/data/2.5/weather?lat="+
                        (float)location.getLatitude() +"&lon=" +
                        (float)location.getLongitude() + "&units=metric&appid=321bef383f11d22c06a3ee16bd48b3d0");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            new LoadWeatherData().execute(url);
            CameraPosition position = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude())) // Sets the new camera position
                    .zoom(10) // Sets the zoom
                    .bearing(180) // Rotate the camera
                    .tilt(30) // Set the camera tilt
                    .build(); // Creates a CameraPosition from the builder

            mapboxMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(position), 7000);
            locationManager.removeUpdates(locListener);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
