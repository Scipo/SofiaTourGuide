package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.List;

import DB.PlacesBaseHelper;
import DB.PlacesCRUD;
import model.Places;

public class DistanceService extends Service {
    private ServiceThread serviceThread;
    private Thread thread;

    public DistanceService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        // Toast.makeText(this, "Distance Service Started", Toast.LENGTH_SHORT).show();
        this.serviceThread = new ServiceThread(getBaseContext());
        this.thread = new Thread(this.serviceThread);
        thread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.thread.interrupt();
        // Toast.makeText(this, "Distance Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
