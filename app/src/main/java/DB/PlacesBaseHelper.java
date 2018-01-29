package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlacesBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static  String DATABASE_NAME ="placeDBase.db";

    public PlacesBaseHelper(Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + PlacesDBSchema.PlaceTable.NAME + "("
                + "_id integer primary key autoincrement," +
                PlacesDBSchema.PlaceTable.Cols.UUID + ", "+
                PlacesDBSchema.PlaceTable.Cols.Code + ", "+
                PlacesDBSchema.PlaceTable.Cols.Name + ", "+
                PlacesDBSchema.PlaceTable.Cols.Description + ", "+
                PlacesDBSchema.PlaceTable.Cols.Address + ", "+
                PlacesDBSchema.PlaceTable.Cols.Schedule + ", " +
                PlacesDBSchema.PlaceTable.Cols.Price + ", " +
                PlacesDBSchema.PlaceTable.Cols.Lat + ", " +
                PlacesDBSchema.PlaceTable.Cols.Lon + ", " +
                PlacesDBSchema.PlaceTable.Cols.ImageResourceId +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){

    }

}
