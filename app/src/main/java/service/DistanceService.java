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
    public DistanceService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Distance Service Started", Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(new ServiceThread(getBaseContext()));

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Distance Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
