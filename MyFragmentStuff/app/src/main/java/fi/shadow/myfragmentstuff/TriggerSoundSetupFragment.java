package fi.shadow.myfragmentstuff;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class TriggerSoundSetupFragment extends Fragment {
    private TextView timeLeft;
    private View view;
    private int soundID;
    private MyBroadCastReceiver myReceiver;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.trigger_sound_setup_fragment, container);
        timeLeft = view.findViewById(R.id.countDown);
        myReceiver = new MyBroadCastReceiver();
        IntentFilter myIntentFilter = new IntentFilter("myIntentFilter");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(myReceiver, myIntentFilter);
        SeekBar mySeekBar = view.findViewById(R.id.mySeekBar);
        mySeekBar.setMax(30);
        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("onProgressChanged", String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button dStart = view.findViewById(R.id.dStart);
        dStart.setOnClickListener(v -> {
            Log.d("SoundItemID: ", String.valueOf(this.soundID));
            Log.d("dStart: onClick", String.valueOf(mySeekBar.getProgress()));
            Intent i = new Intent(view.getContext(), MusicService.class);
            i.putExtra("soundID", this.soundID);
            i.putExtra("delayTime", mySeekBar.getProgress());
            getActivity().startService(i);
        });
        return view;
    }



    public void setSoundID(int soundID){
        this.soundID = soundID;
        TextView itemID = view.findViewById(R.id.itemID);
        itemID.setText("Selected item ID: " + soundID);
    }
    public void setName(String soundName){
        TextView itemName = view.findViewById(R.id.itemName);
        itemName.setText("Selected item name: " + soundName);
    }
    class MyBroadCastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            timeLeft.setText(String.valueOf(intent.getLongExtra("timeLeft", 0) / 1000));

            Log.d("onRecieve", String.valueOf(intent.getLongExtra("timeLeft", 0) / 1000));
        }
    }
}

