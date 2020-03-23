package fi.shadow.mooretraning;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends MyBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Debug.loadDebug(this);
        Debug.print(TAG, "onCreate", "Entering sec onCreate", 1, true);
        TextView tv = findViewById(R.id.text);
        tv.setText("This is second view!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Debug.print(TAG, "onStart", "Entering sec onStart", 1, true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Debug.print(TAG, "onRestart", "Entering sec onRestart", 1, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Debug.print(TAG, "onResume", "Entering sec onResume", 1, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Debug.print(TAG, "onPause", "Entering sec onPause", 1, true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Debug.print(TAG, "onStop", "Entering sec onStop", 1, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.print(TAG, "onDestroy", "Entering sec onDestroy", 1, true);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Debug.print(TAG, "onRestoreInstanceState", "Entering onRestoreInstanceState", 1, true);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Debug.print(TAG, "onSaveInstanceState", "Entering onSaveInstanceState", 1, true);
        super.onSaveInstanceState(outState);
    }
}
