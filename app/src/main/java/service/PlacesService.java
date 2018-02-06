package service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

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
    }

    private boolean isNetworkAvailableAndConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable= cm.getActiveNetworkInfo() !=null;
        boolean isNetworkConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();
        return isNetworkConnected;
    }
}
