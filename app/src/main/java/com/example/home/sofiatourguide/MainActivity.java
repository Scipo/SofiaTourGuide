package com.example.home.sofiatourguide;

import android.support.v4.app.Fragment;
import Fragments.PlacesFragment;


public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return  new PlacesFragment();
    }
}
