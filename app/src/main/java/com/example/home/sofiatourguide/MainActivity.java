package com.example.home.sofiatourguide;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

import Fragments.PlacesFragment;


public class MainActivity extends SingleFragmentActivity {

    private static final String extra_place_id = "com.example.home.placeIntent.place_id";

    public static Intent newIntent(Context packageContext, UUID place_id ){
        Intent intent = new Intent(packageContext,MainActivity.class);
        intent.putExtra(extra_place_id,place_id);
        return intent;
        //Съзадваме "ектсра", което означава че казваме на
        // PlaceFragment кой Place да покаже като му подаваме ID
        // като Intent extra, когато се стартира MainActivity и връщаме Intent
    }

    @Override
    protected Fragment createFragment(){
        UUID placeID = (UUID) getIntent().getSerializableExtra(extra_place_id);
        return PlacesFragment.newInstance(placeID);
    }


}
