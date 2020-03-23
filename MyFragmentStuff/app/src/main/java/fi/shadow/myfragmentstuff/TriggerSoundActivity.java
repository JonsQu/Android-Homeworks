package fi.shadow.myfragmentstuff;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TriggerSoundActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigger_sound);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent data = getIntent();
        String itemName = data.getStringExtra("itemName");
        int itemID = data.getIntExtra("itemID", -1);
        TriggerSoundSetupFragment tssf = (TriggerSoundSetupFragment) getSupportFragmentManager()
                .findFragmentById(R.id.triggerSoundSetupFragment);
        tssf.setName(itemName);
        tssf.setSoundID(itemID);
    }
}
