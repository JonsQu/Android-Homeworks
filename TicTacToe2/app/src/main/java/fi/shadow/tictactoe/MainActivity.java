package fi.shadow.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyBaseActivity {
    private Button[][] gameTable = new Button[3][3];
    private String turn = "X";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.loadDebug(this);
        loadGameTable();
    }

    public void buttonClicked(View view) {
        Button tmp = findViewById(view.getId());
        tmp.setText(turn);
        tmp.setClickable(false);
        winCheck(turn);
        if(turn.equals("X")){
            turn = "O";
        }else if(turn.equals("O")){
            turn = "X";
        }
    }

    private void winCheck(String player){
        int horX = 0;
        int horO  = 0;
        int verX = 0;
        int verO = 0;
        int dieX = 0;
        int dieO = 0;
        int dieo2 = 0;
        int diex2 = 0;
        for(int i = 0; i < 3;i++){
            for(int y = 0;y < 3;y++ ){
                if(gameTable[i][y].getText().equals("X")){
                    horO = 0;
                    horX++;
                }else if(gameTable[i][y].getText().equals("O")){
                    horX = 0;
                    horO++;
                }
                if(gameTable[y][i].getText().equals("X")){
                    verO = 0;
                    verX++;
                }else if(gameTable[y][i].getText().equals("O")){
                    verX = 0;
                    verO++;
                }

            }
            for(int x = 0; x < 3; x++){
                for(int d = x; d == x;d++){
                    if(gameTable[x][d].getText().equals("X")){
                        dieO = 0;
                        dieX++;
                    }
                    if(gameTable[x][d].getText().equals("O")){
                        dieX = 0;
                        dieO++;
                    }
                }
                for(int d2 = 2 - x;d2 == 2 - x; d2--){
                    if(gameTable[x][d2].getText().equals("X")){
                        dieo2 = 0;
                        diex2++;
                    }
                    if(gameTable[x][d2].getText().equals("O")){
                        diex2 = 0;
                        dieo2++;
                    }
                }
            }
            if(horO == 3 || horX == 3 || verO == 3 || verX == 3 || dieO == 3 || dieX == 3 || dieo2 == 3 || diex2 == 3){
                lockGame();
                Toast.makeText(this, player + " Wins the game!", Toast.LENGTH_SHORT).show();
                Button res = findViewById(R.id.reset);
                res.setText("Reset Game!");
                res.setClickable(true);
            }
            horO = 0;
            horX = 0;
            verO = 0;
            verX = 0;
            dieO = 0;
            dieX = 0;
            dieo2 = 0;
            diex2 = 0;
        }
    }
    private void loadGameTable(){
        for(int i = 0; i < 3;i++){
            for(int y = 0;y < 3;y++ ){
                String id = "b" + i + y;
                gameTable[i][y] = findViewById(getResources().getIdentifier(id, "id", getPackageName()));
                gameTable[i][y].setText("");
                gameTable[i][y].setClickable(true);
            }
        }
        Toast.makeText(this, "X Player starts the game!", Toast.LENGTH_LONG).show();
        turn = "X";
    }

    public void resetGame(View view) {
        Button res = findViewById(view.getId());
        res.setText("");
        res.setClickable(false);
        loadGameTable();

    }
    private void lockGame(){
        for(int i = 0; i < 3;i++){
            for(int y = 0;y < 3;y++ ){
                gameTable[i][y].setClickable(false);
            }
        }
    }
}
