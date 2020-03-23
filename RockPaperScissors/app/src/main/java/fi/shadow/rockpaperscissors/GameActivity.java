package fi.shadow.rockpaperscissors;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

public class GameActivity extends AppCompatActivity implements MyDialogFrag.MyDialog {
    private TextView winTxt;
    private TextView pScore;
    private TextView cScore;
    private MyDialogFrag myDialogFrag;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        winTxt = findViewById(R.id.center);
        pScore = findViewById(R.id.psNum);
        cScore = findViewById(R.id.csNum);
        myDialogFrag = new MyDialogFrag();
        gameOn();
    }

    private void gameOn(){
        ImageView playerImg = findViewById(R.id.pImage);
        ImageView computerImg = findViewById(R.id.cImage);
        // player info
        TextView playerNa = findViewById(R.id.pScore);
        TextView playerNu  = findViewById(R.id.psNum);
        playerNa.bringToFront();
        playerNu.bringToFront();
        // computer info
        TextView comNa = findViewById(R.id.cScore);
        TextView comNu = findViewById(R.id.csNum);
        comNa.bringToFront();
        comNu.bringToFront();
        // win text animation
        Log.d("objectStuff", String.valueOf(winTxt.getZ()));
        ObjectAnimator winAnim = ObjectAnimator.ofFloat(winTxt, "RotationY", 90);
        winAnim.setDuration(3000);
        winAnim.setInterpolator(new CycleInterpolator(2));
        winAnim.start();
        //player animation
        Animation playerNN = AnimationUtils.loadAnimation(this, R.anim.bottom_top);
        playerNN.setInterpolator(new FastOutSlowInInterpolator());
        playerNa.startAnimation(playerNN);
        playerNu.startAnimation(playerNN);
        // computer animation
        Animation comNN = AnimationUtils.loadAnimation(this, R.anim.top_bottom);
        comNN.setInterpolator(new FastOutSlowInInterpolator());
        comNa.startAnimation(comNN);
        comNu.startAnimation(comNN);
        int pChoice = getRandomObj();
        int cChoice = getRandomObj();
        checkWin(pChoice, cChoice);
        Animation myplayAnim = AnimationUtils.loadAnimation(this, R.anim.left_right_img_movement);
        Animation mycomAnim = AnimationUtils.loadAnimation(this, R.anim.right_left_img_movement);
        mycomAnim.setInterpolator(new BounceInterpolator());
        myplayAnim.setInterpolator(new BounceInterpolator());
        playerImg.startAnimation(myplayAnim);
        computerImg.startAnimation(mycomAnim);
        playerImg.setImageResource(getImgID(pChoice));
        computerImg.setImageResource(getImgID(cChoice));
    }
    private int getRandomObj(){
        Random myChoice = new Random();
        return myChoice.nextInt(3) +1;
    }
    private int getImgID(int choice){
        switch (choice){
            case 1:
                return getResources().getIdentifier("rock", "drawable", getPackageName());
            case 2:
                return getResources().getIdentifier("paper", "drawable", getPackageName());
            case 3:
                return getResources().getIdentifier("scissors", "drawable", getPackageName());
        }
        return 0;
    }
    private void checkWin(int player, int computer){
        if(player == 1){    //Rock
            if(computer == 1){
                //draw
                textToScreene(3);
            }else if(computer == 2){
                //computer wins
                textToScreene(2);
            }else{
                //player wins
                textToScreene(1);
            }
        }else if(player == 2){  //Paper
            if(computer == 1){
                //player wins
                textToScreene(1);
            }else if(computer == 2){
                //draw
                textToScreene(3);
            }else{
                //computer wins
                textToScreene(2);
            }
        }else if(player == 3){  //Scissors
            if(computer == 1){
                //computer wins
                textToScreene(2);
            }else if(computer == 2){
                //player wins
                textToScreene(1);
            }else{
                //draw
                textToScreene(3);
            }
        }else{  //something is 0...
            Log.d("checkWin", "ERRRROOOR");
            Log.d("checkWin", "player: " + player + " computer: " + computer);
        }
    }
    private void textToScreene(int status){
        winTxt.bringToFront();
        switch (status){
            case 1: //player wins
                winTxt.setText("Player win the game!");
                int getNum = Integer.parseInt((String) pScore.getText());
                pScore.setText(String.valueOf(getNum + 1));
                break;
            case 2: //computer wins
                winTxt.setText("Computer win the game!");
                int getNumC = Integer.parseInt((String) cScore.getText());
                cScore.setText(String.valueOf(getNumC + 1));
                break;
            case 3: //Draw
                winTxt.setText("It is Draw");
        }
    }

    @Override
    public void firstClick() {

    }
}
