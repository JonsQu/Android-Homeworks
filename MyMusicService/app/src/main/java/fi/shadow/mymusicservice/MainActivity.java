package fi.shadow.mymusicservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Intent i;
    private boolean isBinded = false;
    private ServiceConnection myServiceConnection;
    private MusicService musicService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myServiceConnection = new MyServiceConnection();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(!isBinded){
            i = new Intent(this, MusicService.class);
            bindService(i, myServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(isBinded){
            unbindService(myServiceConnection);
        }
    }

    public void startMusic(View view) {
        startService(i);
    }

    public void stopMusic(View view) {
        if(i != null && isBinded){
            unbindService(myServiceConnection);
            stopService(i);
        }
    }

    public void increaseVolume(View view) {
        float volume = musicService.increaseVolume();
        Toast.makeText(this, "Current volume level: " + volume, Toast.LENGTH_SHORT).show();
    }

    public void decreaseVolume(View view) {
        float volume = musicService.decreaseVolume();
        Toast.makeText(this, "Current volume level: " + volume, Toast.LENGTH_SHORT).show();
    }

    private class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalBinder binder = (LocalBinder) service;
            musicService = binder.getService();
            isBinded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBinded = false;
        }
    }
}
