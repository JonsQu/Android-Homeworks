package fi.shadow.myfragmentstuff;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MusicService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LocalBroadcastManager myManager = LocalBroadcastManager.getInstance(this);
        Intent myIntent = new Intent("myIntentFilter");
        MediaPlayer mp =
                MediaPlayer.create(this, intent.getIntExtra("soundID", 0));
        new CountDownTimer(intent.getIntExtra("delayTime", 0) * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("onTick", String.valueOf(millisUntilFinished));
                myIntent.putExtra("timeLeft", millisUntilFinished);
                myManager.sendBroadcast(myIntent);
            }

            @Override
            public void onFinish() {
                mp.start();
            }
        }.start();

        return START_STICKY;
    }
}
