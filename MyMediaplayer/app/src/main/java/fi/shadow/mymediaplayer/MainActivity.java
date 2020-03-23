package fi.shadow.mymediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button p = findViewById(R.id.play);
        p.setClickable(false);
        Button s = findViewById(R.id.stop);
        s.setClickable(false);
        Uri uri = getIntent().getData();
        //uri = Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
        if(uri != null){
            mp = MediaPlayer.create(this, uri);
            p.setClickable(true);
            s.setClickable(true);
        }else{
            Toast.makeText(this, "Please provide sound url address when opening this!!", Toast.LENGTH_LONG).show();
        }
    }

    public void playMusic(View view) {

        mp.start();
    }

    public void stopMusic(View view) {
        mp.stop();
        mp.prepareAsync();
    }

    public void pauseMusic(View view) {
        mp.pause();
    }
}
