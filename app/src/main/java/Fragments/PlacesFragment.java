package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.home.sofiatourguide.R;

import java.util.UUID;

import model.Places;
import service.PlacesService;
import singleton.PlaceLabSingleton;

public class PlacesFragment extends Fragment {

    private Places mPlace;

    private TextView mTitleField;
    private TextView mAddressFiled;
    private TextView mSchedule;
    private TextView mDescripotion;
    private ImageView mImageView;

    private static final String ARG_PLACE_ID = "place_id";

    public static PlacesFragment newInstance(UUID placeID){

        Bundle args = new Bundle();
        args.putSerializable(ARG_PLACE_ID, placeID);

        PlacesFragment fragment = new PlacesFragment();
        fragment.setArguments(args);
        return fragment;
        //Всеки фрагмент може да има Bundel обект прикачен към него. Той съдържа двойка ключ-стойност,
        // и работи като екстрата в MainActivity, като в този случай всяка двойка е аргумрнт.
        //сега PlaceFragment ще извика PlaceFragment.newInstance(UUID), когато е необходимо да създаде
        // PlaceFragment. То ще предаде UUID и изтегли от есктрата. Отива в PlaceActivity и в createFragment,
        // взима екстрата от PlaceActivity интента и го предава на PlaceFragment.newIntent(UUID)
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID plаceID = (UUID) getArguments().getSerializable(ARG_PLACE_ID);
        mPlace = PlaceLabSingleton.get(getActivity()).getPlaces(plаceID);
        //обработва на IDто
    }

    @Override
    public View onCreateView(LayoutInflater  inflater, ViewGroup parent,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frame_places,parent,false);
        mTitleField = (TextView) v.findViewById(R.id.crime_title);

        mImageView = v.findViewById(R.id.crime_image_view);
        mAddressFiled = v.findViewById(R.id.crime_address_text_view);
        mSchedule = v.findViewById(R.id.crime_schedule_text_view);
        mDescripotion = v.findViewById(R.id.crime_description_text_view);

        mTitleField.setText(mPlace.getTitle());
        return v;
        //Връща view-то
    }
}
