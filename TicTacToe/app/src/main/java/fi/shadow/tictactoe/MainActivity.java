package fi.shadow.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends MyBaseActivity {
    private String turn = "x";
    private int gameLenght = 0;
    private ArrayList<Button> gameButtons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Debug.loadDebug(this);
        loadGameButtons();
    }

    public void clearGame(View view) {
        for(int i = 0; i < gameButtons.size(); i++){
            gameButtons.get(i).setClickable(true);
            gameButtons.get(i).setText("");
        }
        gameLenght = 0;
    }

    public void gameButtonClick(View view) {
        if(view.isClickable() && gameLenght < 9){
            Button clicked = findViewById(view.getId());
            if(turn.equals("x")){
                clicked.setText("X");
                turn = "o";
            }else if(turn.equals("o")){
                clicked.setText("O");
                turn = "x";
            }
            //Debug.print(TAG, "gameButtonClick(View view", String.valueOf(clicked.getId()), 3, true);
            clicked.setClickable(false);
            gameLenght++;
            if(gameLenght > 4) {
                boolean winner = winnerCheck(clicked.getText().toString());
                if (winner) {
                    Toast.makeText(this, clicked.getText().toString() + " Wins the game!!!", Toast.LENGTH_LONG).show();
                    clearGame(clicked);
                    turn = "x";
                }
            }
            //Debug.print(TAG, "gameButtonClick(View view", String.valueOf(clicked.getPaddingTop()), 1, true);
        }
        if(gameLenght == 9){
            Toast.makeText(this, "Game is finished and nobody win! Please clear the game and play again!!", Toast.LENGTH_LONG).show();
            clearGame(view);
            turn = "x";
        }
    }
    private void loadGameButtons(){
        TableLayout layout = findViewById(R.id.mainTable);
        int count = layout.getChildCount();
        for(int i = 0; i < count; i++){
            View cView = layout.getChildAt(i);
            if(cView instanceof TableRow){
                TableRow row = (TableRow) cView;
                for(int j = 0;j < row.getVirtualChildCount();j++){
                    gameButtons.add((Button) findViewById(row.getVirtualChildAt(j).getId()));
                }
            }
        }
    }
    private boolean winnerCheck(String player){
        int horWin = 0;
        int verWin1 = 0;
        int verWin2 = 0;
        int verWin3 = 0;
        int diaWin1 = 0;
        int diaWin2 = 0;
        for(int i = 0; i < gameButtons.size(); i++){
            Debug.print(TAG, String.valueOf(gameButtons.size()), player, 2, true);
            if(gameButtons.get(i).getText().toString().equals(player)){
                horWin++;
                if(i == 0){
                    verWin1++;
                    diaWin1++;
                }else if(i == 1){
                    verWin2++;
                }else if(i == 2){
                    diaWin2++;
                    verWin3++;
                }else if(i == 3){
                    verWin1++;
                }else if(i == 4){
                    verWin2++;
                    diaWin1++;
                    diaWin2++;
                }else if(i == 5){
                    verWin3++;
                }else if(i == 6){
                    verWin1++;
                    diaWin2++;
                }else if(i == 7){
                    verWin2++;
                }else if(i == 8){
                    verWin3++;
                    diaWin1++;
                }
            }else{
                horWin = 0;
            }
            if(horWin == 3 || verWin1 == 3 || verWin2 == 3 || verWin3 == 3 || diaWin1 == 3 || diaWin2 == 3){
                return true;
            }
            if(i == 2 || i == 5){
                horWin = 0;
            }

        }
        return false;
    }
}
