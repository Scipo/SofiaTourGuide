package com.example.home.sofiatourguide;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import Fragments.PlacesListFragment;

public class PlacesListActivity extends SingleFragmentActivity{
   public static Intent newIntent(Context context){
       return new Intent(context, PlacesListActivity.class);
        //връща инстанция която ще стартиа PlaceListActivity
        // като от тук ще се извика Notification от сървиса и той евентулано ще се стартира
    }
    @Override
    protected Fragment createFragment(){
        return new PlacesListFragment();
    }
}
