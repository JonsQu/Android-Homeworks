package fi.shadow.dreamcrusher;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainActivity extends MyBaseActivity {
    private SortedSet<Integer> buttons = new TreeSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.loadDebug(this);
    }

    public void lottoNumberPressed(View v){
        Debug.print(TAG, "lottoNumberPressed()", String.valueOf(getResources().getIdentifier("b5", "id", getPackageName())), 1, true);
        Button n = (Button) v;
        if(buttons.size() <= 6) {
            
            n.setBackgroundColor(getResources().getColor(R.color.myBlue));
            //Button tmp = findViewById(v.getId());
            buttons.add(v.getId());
        }else if(buttons.size() > 6){
            Button ifl = findViewById(R.id.ifl);
            ifl.setClickable(true);
        }


    }

    public void iFeelLucky(View view) {
        buttons.comparator();
        for(Integer b: buttons){
            Debug.print(TAG, "iFeelLucky()", b.toString(), 2, true);
            Debug.print(TAG, "iFeelLucky()", findViewById(b).toString(), 2, true);
        }
    }
}
