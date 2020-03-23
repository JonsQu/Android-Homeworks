package com.example.haavi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Logging my application." );
    }

    public void count(View view) {
        EditText mass = findViewById(R.id.mass);
        EditText height = findViewById(R.id.height);
        TextView countedBMI = findViewById(R.id.countText);
        try{
            double massConv = Double.parseDouble(mass.getText().toString());
            double heightConv = Double.parseDouble(height.getText().toString());
            DecimalFormat df = new DecimalFormat("###.###");
            countedBMI.setText(countedBMI.getText() + String.valueOf(df.format((1.3*massConv)/
                    (Math.pow(heightConv/100, 2.5)))));
        }catch (NumberFormatException e){
            Toast.makeText(this, "Use Numbers!", Toast.LENGTH_SHORT).show();
        }

    }
}
