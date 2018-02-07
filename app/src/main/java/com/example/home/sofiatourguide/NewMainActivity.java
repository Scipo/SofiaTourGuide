package com.example.home.sofiatourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        this.populatePlaces();
        Intent intent = new Intent(this, DistanceService.class);
        startService(intent);
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
