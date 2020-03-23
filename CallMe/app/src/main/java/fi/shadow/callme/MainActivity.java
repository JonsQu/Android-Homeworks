package fi.shadow.callme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int REQUEST_CODE = 13;
    private String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callMe(View view) {
        if(phoneNum != null) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
            startActivity(intent);
        }else{
            Toast.makeText(this, "Please go setting and write you number!", Toast.LENGTH_LONG).show();
        }
    }

    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("landCode", "+358");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                System.out.println(bundle.getString("phoneNum"));
                this.phoneNum = bundle.getString("phoneNum");
            }
        }
    }

    public void openBrowser(View view) {
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        //startActivity(intent);
        Intent i = new Intent(fi.shadow.mymediaplayer.PLAYSOUND, Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"));
        startActivity(i);
    }


}
