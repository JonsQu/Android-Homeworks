package fi.shadow.mymusicservice;

import android.app.Service;
import android.os.Binder;
import android.os.IBinder;

class LocalBinder extends Binder {
    private MusicService myService;
    public LocalBinder (MusicService service){
        this.myService = service;
    }

    public MusicService getService(){
        return this.myService;
    }
}
