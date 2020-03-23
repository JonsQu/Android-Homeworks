package fi.shadow.tictactoe;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import fi.shadow.tictactoe.BuildConfig;
import fi.shadow.tictactoe.R;

public class Debug {
    private static int DEBUG_LEVEL;
    private static Context context;

    public static void print(String ClassName, String MethodName, String Message, int level, boolean Console){
        if(BuildConfig.DEBUG && level <= DEBUG_LEVEL){
            if(Console){
                Log.d(ClassName, MethodName + ", " + Message);
            }else{
                Toast.makeText(context, ClassName + ", " + MethodName + ", " + Message, Toast.LENGTH_LONG).show();
            }

        }
    }

    public static void loadDebug(Context host){
        context = host.getApplicationContext();
        DEBUG_LEVEL = host.getResources().getInteger(R.integer.debugLevel);
    }
}
