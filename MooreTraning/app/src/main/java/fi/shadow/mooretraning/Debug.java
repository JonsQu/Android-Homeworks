package fi.shadow.mooretraning;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Debug {
    private static int DEBUG_LEVEL = 1;
    private static Context DHost;
    public static void print(String clasName,String methodName, String msg, int level,boolean console){
        if(level <= DEBUG_LEVEL){
            if(BuildConfig.DEBUG){
                if(console) {
                    Log.d(clasName, methodName + ", " + msg);
                }else{
                    Toast.makeText(DHost,clasName + ", " + methodName + ", " + msg, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    public static void loadDebug(Context host){
        DHost = host;
        DEBUG_LEVEL = R.integer.debug_level;
    }
}
