package fi.shadow.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title = findViewById(R.id.head);
        Animation myTestAnimation = AnimationUtils.loadAnimation(this, R.anim.my_view_animation);
        myTestAnimation.setRepeatCount(3);
        myTestAnimation.setRepeatMode(2);
        title.startAnimation(myTestAnimation);
        Button startGame = findViewById(R.id.startGame);
        Button highScore = findViewById(R.id.highScore);
        ObjectAnimator oAnim = ObjectAnimator.ofFloat(startGame, "X", 1500);
        oAnim.setRepeatMode(ObjectAnimator.REVERSE);
        oAnim.setRepeatCount(1);
        oAnim.setDuration(1000);
        oAnim.start();
        ObjectAnimator hAnim = ObjectAnimator.ofFloat(highScore, "rotation", 0f, 360f);
        hAnim.setDuration(3000);
        hAnim.start();
    }

    public void startMyGame(View view) {
        Intent game = new Intent(this, GameActivity.class);
        startActivity(game);
    }
}
