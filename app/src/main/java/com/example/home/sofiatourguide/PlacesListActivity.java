package com.example.home.sofiatourguide;
import android.support.v4.app.Fragment;

import Fragments.PlacesListFragment;

public class PlacesListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment(){
        return new PlacesListFragment();
    }
}
