package com.example.home.sofiatourguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

import Fragments.PlacesFragment;
import model.Places;
import singleton.PlaceLabSingleton;

public class PlacesPagerActivity extends FragmentActivity{

    private static final String extra_place_id = "com.example.home.placeIntent.place_id";

    private ViewPager mViewPager;
    private List<Places> mPlaces;

    public static Intent newIntent(Context packageContext, UUID placeId ){
        Intent intent = new Intent(packageContext,PlacesPagerActivity.class);
        intent.putExtra(extra_place_id, placeId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_viewpager);

        UUID placeId = (UUID) getIntent().getSerializableExtra(extra_place_id);

        mViewPager = (ViewPager) findViewById(R.id.activity_place_pager_ViewPager);
        mPlaces = PlaceLabSingleton.get(this).getPlaces();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Places places = mPlaces.get(position);
                return PlacesFragment.newInstance(places.getId());
            }

            @Override
            public int getCount() {
                return mPlaces.size();
            }
        });
        for(int i=0; i<mPlaces.size();i++){
            if(mPlaces.get(i).getId().equals(placeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
