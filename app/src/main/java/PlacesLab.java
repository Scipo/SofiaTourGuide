import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class PlacesLab {
    private static PlacesLab sPlaces;

    private List<Places>mPlace;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    private PlacesLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new PlacesBaseHelper(mContext).getWritableDatabase();
        mPlace = new ArrayList<>();
    }
}
