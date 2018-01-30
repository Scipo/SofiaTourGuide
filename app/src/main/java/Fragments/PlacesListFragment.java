package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.sofiatourguide.R;

import java.util.List;

import model.Places;
import singleton.PlaceLabSingleton;

public class PlacesListFragment extends Fragment {

    private RecyclerView mPlacesRecycleView;
    private PlaceAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list_recycleview,container,false);

        mPlacesRecycleView = (RecyclerView) view.findViewById(R.id.places_recycle_view);
        mPlacesRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }
    private void updateUI(){
        PlaceLabSingleton placeLabSingleton = PlaceLabSingleton.get(getActivity());
        List<Places> places = placeLabSingleton.getPlaces();

        mAdapter = new PlaceAdapter(places);
        mPlacesRecycleView.setAdapter(mAdapter);
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
            Toast.makeText(getActivity(), mPlaces.getTitle(),Toast.LENGTH_SHORT).show();
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
