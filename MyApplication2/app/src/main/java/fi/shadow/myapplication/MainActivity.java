package fi.shadow.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.loadDebug(this);
        //Command used: adb logcat fi.shadow.myapplication:D *:S
    }

    public void CalculateBMI(View view) {
        EditText eMass = findViewById(R.id.mass);
        EditText eHeight = findViewById(R.id.height);
        double mass = getUserDouble(eMass);
        double height = getUserDouble(eHeight);
        if(mass != 0 && height != 0) {
            TextView result = findViewById(R.id.result);
            double calculate = (1.3f * mass) / (Math.pow(height / 100, 2.5f));
            DecimalFormat f = new DecimalFormat("##.##");
            result.setText(String.valueOf(f.format(calculate)));
        }
        //System.out.println(mass);
        //System.out.println(height);
    }
    private double getUserDouble(EditText t){
        double testing = 0;
        Debug.print(TAG,"getUserDouble(EditText t)","t = " + t.getText(),1,false);
        try{
            Debug.print(TAG,"getUserDouble(EditText t)","In TryCatch level 2",2,true);
            testing = Double.parseDouble(String.valueOf(t.getText()));
        }catch (NumberFormatException e){
            e.printStackTrace();
            //Toast.makeText(this,"Use only Number!",Toast.LENGTH_SHORT).show();
        }
        return testing;
    }
}
