package fi.shadow.callme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {
    private String landCode;
    private EditText phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        landCode = getIntent().getExtras().getString("landCode");
        this.phoneNum = findViewById(R.id.phoneNum);
        phoneNum.setText(landCode);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("phoneNum",phoneNum.getText().toString());
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
