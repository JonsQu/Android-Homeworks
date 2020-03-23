package fi.shadow.shadowservice;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import java.util.Random;

import androidx.annotation.Nullable;

public class MyTestService extends Service {
    private boolean isRunning = false;
    private Thread t;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy(){
        isRunning = false;
        System.out.println("Thread is alive? " + t.isAlive());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread killed");
        System.out.println("Thread is alive? " + t.isAlive());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        Random ran = new Random();
        int[] arr = new int[intent.getIntExtra("size", 0)];
        if(arr.length > 0 ) {
            t = new Thread(() -> {
                for (int i = 0; i < arr.length && isRunning; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        arr[i] = ran.ints(intent.getIntExtra
                                ("min", 0), (intent.getIntExtra("max", 0)) +1).findFirst().getAsInt();
                    }
                }
                int[] sotArr = doSelectionSort(arr, intent.getIntExtra("find", 0));
                for (int i = 0; i < sotArr.length && isRunning; i++) {
                    if (sotArr[i] == intent.getIntExtra("find", 0)) {
                        System.out.println(sotArr[i] + " Found at index " + i);
                    }
                    //System.out.println(i);
                }
            });
        /*Thread t = new Thread(() -> {
            for(int i = 1; i < 11; i++){
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/
            t.start();
        }
        return START_STICKY;
    }
    private int[] doSelectionSort(int[] arr, int find){

        for (int i = 0; i < arr.length - 1 && isRunning; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length && isRunning; j++){
                if (arr[j] < arr[index]) {
                    index = j;
                }
                int smallerNumber = arr[index];
                arr[index] = arr[i];
                arr[i] = smallerNumber;
            }
        }
        return arr;
    }

}
