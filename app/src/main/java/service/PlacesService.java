package service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.home.sofiatourguide.PlacesListActivity;
import com.example.home.sofiatourguide.R;

public class PlacesService extends IntentService {

    private static final String ServiceTAG = "PlaceService";

    public static Intent newIntent(Context context){
        return new Intent(context,PlacesService.class);
    }
    public PlacesService(){
        super(ServiceTAG);
    }

    @Override
    protected void onHandleIntent(Intent intent){
        if(!isNetworkAvailableAndConnected()){
            return;
        }
        Log.i(ServiceTAG,"Received Intent: " + intent);
        Resources resources = getResources();

        Intent i = PlacesListActivity.newIntent(this);
        Notification notification = new Notification.Builder(this)
                .setContentTitle(resources.getString(R.string.crime_title_hint))
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0,notification);

    }

    private boolean isNetworkAvailableAndConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable= cm.getActiveNetworkInfo() !=null;
        boolean isNetworkConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();
        return isNetworkConnected;
    }
}
