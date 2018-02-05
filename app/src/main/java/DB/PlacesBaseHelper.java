package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlacesBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME ="placeDBase.db";
    private static final String SQL_CREATE_PLACES_TABLE =
        "CREATE TABLE " + Places.TABLE_NAME + " (" +
                Places.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Places.NAME + " TEXT," +
                Places.DESCRIPTION + " TEXT," +
                Places.ADDRESS + " TEXT," +
                Places.SCHEDULE + " TEXT," +
                Places.PRICE + " REAL," +
                Places.LAT + " REAL," +
                Places.LON + " REAL," +
                Places.IMAGE_ID + " TEXT);";
    private static final String SQL_DROP_PLACES_TABLE = "DROP TABLE IF EXISTS " + Places.TABLE_NAME + ";";
    private static final String LOG_TAG = "database_log";

    // places table name and columns
    private static final class Places {
        public static final String TABLE_NAME = "places";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String ADDRESS = "address";
        public static final String SCHEDULE = "schedule";
        public static final String PRICE = "price";
        public static final String LAT = "lat";
        public static final String LON = "lon";
        public static final String IMAGE_ID = "image_id";
    }

    public PlacesBaseHelper(Context context){

        super(context,DATABASE_NAME,null, VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        /*db.execSQL("create table " + PlacesDBSchema.PlaceTable.TABLE_NAME + "("
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
        );*/

        db.execSQL(SQL_CREATE_PLACES_TABLE);
        Log.i(LOG_TAG, "Database and places table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        db.execSQL(SQL_DROP_PLACES_TABLE);
        onCreate(db);
        Log.i(LOG_TAG, "Database and places table upgraded");
    }

}
