package fi.shadow.myfragmentstuff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ChosenSound {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemSelected(SoundItem soundItem) {
        if(getSupportFragmentManager().findFragmentById(R.id.triggerSoundSetupFragment) != null){
            TriggerSoundSetupFragment tssf =
                    (TriggerSoundSetupFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.triggerSoundSetupFragment);
            tssf.setName(soundItem.getName());
            Log.d("itemSelected", String.valueOf(soundItem.getSoundID()));
            tssf.setSoundID(soundItem.getSoundID());
        }else{
            Intent i = new Intent(this, TriggerSoundActivity.class);
            i.putExtra("itemName", soundItem.getName());
            i.putExtra("itemID", soundItem.getSoundID());
            startActivity(i);
        }
        Log.d("itemSelected", soundItem.toString());
    }
}
