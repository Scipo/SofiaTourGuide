package com.example.home.sofiatourguide;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DB.PlacesBaseHelper;
import DB.PlacesCRUD;
import model.Places;
import service.DistanceService;

public class NewMainActivity extends AppCompatActivity {
    private ListView placesListView;
    private LocationManager locationManager;
    private LocationListener listener;
    // https://github.com/miskoajkula/Gps_location/blob/master/app/src/main/java/testing/gps_location/MainActivity.java
    // https://www.youtube.com/watch?v=QNb_3QKSmMk

    public static double latitude;
    public static double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        this.populatePlaces();
        Intent intent = new Intent(this, DistanceService.class);
        startService(intent);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        locationManager.requestLocationUpdates("gps", 30 * 60 * 1000, 0, listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, DistanceService.class);
        stopService(intent);
    }

    private void populatePlaces() {
        this.placesListView = findViewById(R.id.list_view_places);

        PlacesCRUD crud = new PlacesCRUD(new PlacesBaseHelper(this));
        final List<Places> allPlaces = crud.GetAll();
        List<String> titles = new ArrayList<>();
        for (Places place: allPlaces) {
            titles.add(place.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.places_list_view, titles);
        this.placesListView.setAdapter(adapter);
        this.placesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String chosen = (String) placesListView.getItemAtPosition(i);
                Intent intent = new Intent(NewMainActivity.this, PlaceDetails.class);
                intent.putExtra("place", allPlaces.get(i));
                startActivity(intent);
            }
        });
    }
}
