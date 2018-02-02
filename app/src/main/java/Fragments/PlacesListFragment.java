package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home.sofiatourguide.MainActivity;
import com.example.home.sofiatourguide.PlacesPagerActivity;
import com.example.home.sofiatourguide.R;

import java.util.List;

import model.Places;
import singleton.PlaceLabSingleton;

public class PlacesListFragment extends Fragment {

    private RecyclerView mPlacesRecycleView;
    private PlaceAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);// необходим е за да получава колбеци от менюто
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list_recycleview,container,false);

        mPlacesRecycleView = (RecyclerView) view.findViewById(R.id.places_recycle_view);
        mPlacesRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_place_list_menu,menu);
    }
    //Менюто се управлява чрез колбеци от Activity класът.
    //Когато имаме нуждаот менюто Андроид извиква onCreateOptionsMenu на Activity класът
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_add_places:
                Places places = new Places();
                PlaceLabSingleton.get(getActivity()).addPlaces(places);
                Intent intent = PlacesPagerActivity.newIntent(getActivity(),places.getId());
                startActivity(intent);
                return true;//не е необходим по нататъчен процес след като е избрано
            default:
                return super.onOptionsItemSelected(item);
        }
        //необходимостта на този метод е за да има respond когато се избере от MenuItem
    }

    private void updateUI(){
        PlaceLabSingleton placeLabSingleton = PlaceLabSingleton.get(getActivity());
        List<Places> places = placeLabSingleton.getPlaces();
        if(mAdapter ==null) {
            mAdapter = new PlaceAdapter(places);
            mPlacesRecycleView.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
        //ако има промяна в активитито тя се отбелязва, като в случая notifyDataSetChanged е
        // лесен начин да се презаредят всички съществуващи айтъми, които са видими
    }

    private class PlacesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Places mPlaces;
        private TextView mTitleTextView;

        private PlacesHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_palaces_title);
        }

        private void bindPlaces(Places places) {
            mPlaces = places;
            mTitleTextView.setText(mPlaces.getTitle());
        }

        @Override
        public void onClick(View v){
            Intent intent = PlacesPagerActivity.newIntent(getActivity(),mPlaces.getId());
            startActivity(intent);//Стартира активити от фрагмент->това идва от PlacesPagerActivity
        }

    }

    private class PlaceAdapter extends RecyclerView.Adapter<PlacesHolder> {

        private List<Places> mPlaces;

        private PlaceAdapter(List<Places> places) {
            mPlaces = places;
        }


        @Override
        public PlacesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_place_items, parent, false);
            return new PlacesHolder(view);
        }

        @Override
        public void onBindViewHolder(PlacesHolder holder, int position) {
            Places places =mPlaces.get(position);
            holder.bindPlaces(places);

        }
        @Override
        public int getItemCount(){
            return mPlaces.size();
        }

    }
}
