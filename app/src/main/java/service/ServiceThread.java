package service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.home.sofiatourguide.NewMainActivity;
import com.example.home.sofiatourguide.R;

import java.util.List;

import DB.PlacesBaseHelper;
import DB.PlacesCRUD;
import model.Places;

/**
 * Created by Mitko on 7.2.2018 г..
 */

// DistanceService logic extracted on a separate thread
public class ServiceThread implements Runnable {
    private PlacesCRUD crud;
    private LocationService locationService;
    private Context context;

    public ServiceThread(Context context) {
        this.context = context;
        this.crud = new PlacesCRUD(new PlacesBaseHelper(context));
        this.locationService = new LocationService(context);
    }

    private Location getCurrentLocation() {
        // TODO: да намира текущите географски координати използвайки GPS
        // https://stackoverflow.com/questions/1513485/how-do-i-get-the-current-gps-location-programmatically-in-android

        // throw new UnsupportedOperationException("TODO");
        return this.locationService.getLocation();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            List<Places> allPlaces = crud.GetAll();
            Places nearestPlace = null;

            Double minDiff = Double.MAX_VALUE;

            for(Places place: allPlaces){
                Double diff = Math.abs((place.getLat() + place.getLon()) -
                        (NewMainActivity.latitude + NewMainActivity.longitude));
                if(minDiff > diff){
                    minDiff = diff;
                    nearestPlace = place;
                }
            }

            if(nearestPlace != null){
                Log.i("PLACE", nearestPlace.getTitle());
                // https://stackoverflow.com/questions/1207269/sending-a-notification-from-a-service-in-android
                // TODO: тъпия notification
//                Intent intent = new Intent();
//                PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
//                Notification.Builder notification = new Notification.Builder(context)
//                        .setTicker("TickerTitle")
//                        .setContentTitle("Content Title")
//                        .setContentText("Content Text");
            }

            try {
                Thread.sleep(30 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}