package fi.shadow.shadowservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSearch(View view) {
        EditText size = findViewById(R.id.size);
        EditText min = findViewById(R.id.min);
        EditText max = findViewById(R.id.max);
        EditText find = findViewById(R.id.find);
        i = new Intent(this, MyTestService.class);
        i.putExtra("size", getInt(size));
        i.putExtra("min", getInt(min));
        i.putExtra("max", getInt(max));
        i.putExtra("find", getInt(find));
        startService(i);
    }
    private int getInt(EditText t){
        int getInt = 0;
        try{
            getInt = Integer.parseInt(String.valueOf(t.getText()));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return getInt;
    }

    public void stopSearch(View view) {
        stopService(i);
    }

    public void intentServiceStart(View view) {
        EditText size = findViewById(R.id.size);
        EditText min = findViewById(R.id.min);
        EditText max = findViewById(R.id.max);
        EditText find = findViewById(R.id.find);
        i = new Intent(this, MyTestService.class);
        i.putExtra("size", getInt(size));
        i.putExtra("min", getInt(min));
        i.putExtra("max", getInt(max));
        i.putExtra("find", getInt(find));
        startService(i);
    }
}
