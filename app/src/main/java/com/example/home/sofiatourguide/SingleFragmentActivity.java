package com.example.home.sofiatourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import DB.PlacesBaseHelper;
import DB.PlacesCRUD;
import model.Places;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }

        // creates database
        PlacesBaseHelper db = new PlacesBaseHelper(this);
        /*PlacesCRUD crud = new PlacesCRUD(db);
        List<Places> allPlaces = crud.GetAll();
        Toast.makeText(this, allPlaces.get(0).getTitle(), Toast.LENGTH_SHORT).show();*/
    }
}
