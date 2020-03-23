package fi.shadow.mymusicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    private MediaPlayer mp;
    private IBinder myBinder;
    private final float MAX_VOLUME = 1.0f;
    private float currentLevel = 0.5f;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        mp.setVolume(currentLevel, currentLevel);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mp.stop();
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.my_sound);
        myBinder = new LocalBinder(this);
    }
    public float increaseVolume(){
        if(currentLevel < MAX_VOLUME){
            currentLevel += 0.1f;
            mp.setVolume(currentLevel, currentLevel);
        }
        return currentLevel;
    }
    public float decreaseVolume(){
        if(currentLevel > 0){
            currentLevel -= 0.1f;
            mp.setVolume(currentLevel, currentLevel);
        }
        return currentLevel;
    }
}
