package fi.shadow.mooretraning;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends MyBaseActivity {
    private String time;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.loadDebug(this);
        Debug.print(TAG, "onCreate", "Entering onCreate", 1, true);
        tv = findViewById(R.id.time);
        Calendar c = Calendar.getInstance();
        time = c.getTime().toString();
        tv.setText(time);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Debug.print(TAG, "onStart", "Entering onStart", 1, true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Debug.print(TAG, "onRestart", "Entering onRestart", 1, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Debug.print(TAG, "onResume", "Entering onResume", 1, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Debug.print(TAG, "onPause", "Entering onPause", 1, true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Debug.print(TAG, "onStop", "Entering onStop", 1, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.print(TAG, "onDestroy", "Entering onDestroy", 1, true);
    }

    public void letsMove(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Debug.print(TAG, "onRestoreInstanceState", "Entering onRestoreInstanceState", 1, true);
        if(savedInstanceState != null){
            String value = savedInstanceState.getString("time");

            if(value != null){
                this.time = value;
                this.tv.setText(value);
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Debug.print(TAG, "onSaveInstanceState", "Entering onSaveInstanceState", 1, true);
        outState.putString("time", this.time);
        super.onSaveInstanceState(outState);
    }
}
