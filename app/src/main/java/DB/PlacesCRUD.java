package DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.home.sofiatourguide.R;

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
            int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(PlacesBaseHelper.Places.IMAGE_ID));

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
        List<Places> seedPlaces = new ArrayList<>();

        seedPlaces.add(new Places(
                "Alexander Nevsky Cathedral",
                "",
                "The St. Alexander Nevsky Cathedral (Bulgarian: Храм-паметник „Свети Александър Невски“, Hram-pametnik „Sveti Aleksandar Nevski“) is a Bulgarian Orthodox cathedral in Sofia, the capital of Bulgaria. Built in Neo-Byzantine style, it serves as the cathedral church of the Patriarch of Bulgaria and it is one of the largest Eastern Orthodox cathedrals in the world, as well as one of Sofia's symbols and primary tourist attractions. The St. Alexander Nevsky Cathedral in Sofia occupies an area of 3,170 square metres (34,100 sq ft) and can hold 10,000 people inside. It is the second-largest cathedral located on the Balkan Peninsula, after the Cathedral of Saint Sava in Belgrade",
                "st. Alexander Nevsky square",
                "07:00 – 19:00",
                0.,
                42.6958103,
                23.332901,
                R.drawable.alexander_nevsky
        ));

        seedPlaces.add(new Places(
                "National Historical Museum",
                "",
                "The National Historical Museum (Национален исторически музей, Natsionalen istoricheski muzey) in Sofia is Bulgaria's largest museum. It was founded on 5 May 1973. A new representative exhibition was opened in the building of the Court of Justice on 2 March 1984, to commemorate the 13th centenary of the Bulgarian state.",
                "16 Vitoshko lale str.",
                "9:30 - 18:00",
                10.,
                42.6549786,
                23.270883,
                R.drawable.national_historical_museum
        ));

        seedPlaces.add(new Places(
                "National Palace of Culture",
                "",
                "",
                "1 Bulgaria bul.",
                "9:00 - 19:00",
                0.,
                42.6847265,
                23.3189374,
                R.drawable.ndk
        ));

        for (Places place: seedPlaces) {
            this.insert(place);
        }
    }
}
