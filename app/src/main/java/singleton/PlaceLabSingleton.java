package singleton;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Places;

public class PlaceLabSingleton {
    private static PlaceLabSingleton sPlace_lab;
    private List<Places> mPlaces;

    public static PlaceLabSingleton get(Context context){
        if(sPlace_lab == null){
            sPlace_lab = new PlaceLabSingleton(context);
        }
        return sPlace_lab;
    }
    //Create/Load Objects
    private PlaceLabSingleton(Context context){
        mPlaces = new ArrayList<>();
        for(int i=0; i<100;i++){
            Places places = new Places();
            places.setTitle("Place #" + i);
            mPlaces.add(places);
        }
    }
    public List<Places>getPlaces(){
        return  mPlaces;
    }
    public Places getPlaces(UUID id){
        for(Places places:mPlaces){
            if(places.getId().equals(id)){
                return places;
            }
        }
        return null;
    }
}
