package DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Places;

/**
 * Created by Mitko on 6.2.2018 г..
 */

// more info: https://developer.android.com/training/data-storage/sqlite.html
public class PlacesCRUD {
    private PlacesBaseHelper dbHelper;

    public PlacesCRUD(PlacesBaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // returns false if the operation has failed
    public boolean insert(Places place) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PlacesBaseHelper.Places.UUID, place.getId().toString());
        values.put(PlacesBaseHelper.Places.NAME, place.getTitle());
        values.put(PlacesBaseHelper.Places.CODE, place.getCode());
        values.put(PlacesBaseHelper.Places.DESCRIPTION, place.getDescription());
        values.put(PlacesBaseHelper.Places.ADDRESS, place.getAddress());
        values.put(PlacesBaseHelper.Places.SCHEDULE, place.getSchedule());
        values.put(PlacesBaseHelper.Places.PRICE, place.getPrice());
        values.put(PlacesBaseHelper.Places.LAT, place.getLat());
        values.put(PlacesBaseHelper.Places.LON, place.getLon());
        values.put(PlacesBaseHelper.Places.IMAGE_ID, place.getmImageResourceId());

        long result = db.insert(PlacesBaseHelper.Places.TABLE_NAME, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void delete(Places place) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = PlacesBaseHelper.Places.UUID + " LIKE ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = { place.getId().toString() };

        // Issue SQL statement.
        db.delete(PlacesBaseHelper.Places.TABLE_NAME, selection, selectionArgs);
    }

    private Cursor getRawData() {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                PlacesBaseHelper.Places.UUID,
                PlacesBaseHelper.Places.NAME,
                PlacesBaseHelper.Places.CODE,
                PlacesBaseHelper.Places.DESCRIPTION,
                PlacesBaseHelper.Places.ADDRESS,
                PlacesBaseHelper.Places.SCHEDULE,
                PlacesBaseHelper.Places.PRICE,
                PlacesBaseHelper.Places.LAT,
                PlacesBaseHelper.Places.LON,
                PlacesBaseHelper.Places.IMAGE_ID
        };

        Cursor cursor = db.query(
                PlacesBaseHelper.Places.TABLE_NAME,       // The table to query
                projection,                               // The columns to return
                null,                             // The columns for the WHERE clause
                null,                          // The values for the WHERE clause
                null,                             // don't group the rows
                null,                              // don't filter by row groups
                null                                // The sort order
        );

        return cursor;
    }

    public List<Places> GetAll() {
        Cursor cursor = this.getRawData();

        List places = new ArrayList<>();
        while(cursor.moveToNext()) {
            String uuid = cursor.getString(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.UUID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.NAME));
            String code = cursor.getString(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.CODE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.DESCRIPTION));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.ADDRESS));
            String schedule = cursor.getString(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.SCHEDULE));
            Double price = cursor.getDouble(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.PRICE));
            Double lat = cursor.getDouble(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.LAT));
            Double lon = cursor.getDouble(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.LON));
            String imageId = cursor.getString(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.IMAGE_ID));

            Places current = new Places(
                    UUID.fromString(uuid),
                    name,
                    code,
                    description,
                    address,
                    schedule,
                    price,
                    lat,
                    lon,
                    imageId);

            places.add(current);
        }

        cursor.close();

        return places;
    }

    public void Seed() {
        for (int i = 0; i < 3; i++) {
            Places place = new Places(
                    "title " + Integer.toString(i + 1),
                    "1234",
                    "1234",
                    "1234",
                    "1234",
                    1.0,
                    1.0,
                    1.0,
                    "1234");

            this.insert(place);
        }
    }
}
