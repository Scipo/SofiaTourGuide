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

    // find by uuid

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
                "The National Palace of Culture (Национален дворец на културата, Natsionalen dvorets na kulturata; abbreviated as НДК, NDK), located in Sofia, the capital of Bulgaria, is the largest, multifunctional conference and exhibition centre in south-eastern Europe. It was opened in 1981 in celebration of Bulgaria's 1300th anniversary.\n" +
                        "\n" +
                        "The centre was initiated at the suggestion of Lyudmila Zhivkova, daughter of the communist leader of the former People's Republic of Bulgaria Todor Zhivkov. The project was designed by a team of Bulgarian and foreign architects led by Alexander Georgiev Barov (1931–1999) along with Ivan Kanazirev. The landscaping of Bulgaria Square in front of the National Palace of Culture was designed by another team of architects and landscape engineers, led by Atanas Agura. Internally, the building exhibits a unified style, employing an octagonal motif and heavy, dark colours. Large bright murals depicting historical figures and events cover the main wall of many of the smaller halls.",
                "1 Bulgaria bul.",
                "9:00 - 19:00",
                0.,
                42.6847265,
                23.3189374,
                R.drawable.ndk
        ));

        seedPlaces.add(new Places(
                "National Art Gallery",
                "",
                "The National Museum of Bulgarian Art is the largest and most respectable institution of its kind in Bulgaria. Over the years, it has established its position as a center for national cultural heritage and values.\n" + " The collection was started in 1892 when the People’s Museum of Archeology founded its Department of Art. By virtue of an express resolution of the Council of Ministers in 1948 NMBA (National Art Gallery) was pronounced an independent entity. ",
                "1, Knyaz Alexander I Sqr.",
                "10:00 - 18:00",
                10.,
                42.6963229,
                23.3248921,
                R.drawable.nacgaleria
                ));
        seedPlaces.add(new Places(
                "National museum of natural history",
                "",
                "NMNHS is the only national institution directly engaged with the preservation of scientific collections of live and non-live nature from Bulgaria and the world. The study of biodiversity, environmental protection and the evolution of organisms are the museum’s major priorities",
                "1 Tsar Osvoboditel Blvd",
                "10:00-18:00",
                5.,
                42.6963229,
                23.3248921,
                R.drawable.national_natural_history_museum
        ));
        seedPlaces.add(new Places(
                "Sofia Art Gallery",
                "",
                "In 1928, on the occasion of the celebrations of the semi-centennial anniversary of the Liberation of Sofia and the Millennium of the Golden Age of the Bulgarian literacy and culture, the then Mayor of the city General Vladimir Vazov issued a decree for setting up a special commission which would identify the methods and means for establishment of Sofia Municipal Museum.",
                "1, Gen. Gurko str.",
                "10:00 - 18:30",
                0.,
                42.6963229,
                23.3248921,
                R.drawable.sghg
        ));
        seedPlaces.add(new Places(
           "Serdika Amphitheater",
             "",
             "The Arena of Serdica is 60.5 m long and 43 m wide. However, the Amphitheatre of Serdica is the only one in the world, combining a Roman theatre and a late antique amphitheatre in one place and the only such public building in the Balkans.",
              "4, Budapeshta str.",
                "24/7",
                0.,
                42.6960034,
                23.3271875,
                R.drawable.coliseum
        ));
        seedPlaces.add(new Places(
                "Boyana Church",
                "",
                "The Boyana Church (Bulgarian: Боянска църква, Boyanska tsărkva) is a medieval Bulgarian Orthodox church situated on the outskirts of Sofia, the capital of Bulgaria, in the Boyana quarter. In 1979, the building was added to the UNESCO World Heritage List.\n" +
                        "\n" +
                        "The east wing of the two-storey church was originally constructed in the late 10th or early 11th century, then the central wing was added in the 13th century under the Second Bulgarian Empire, the whole building being finished with a further expansion to the west in the middle of the 19th century. A total of 89 scenes with 240 human images are depicted on the walls of the church.",
                "3 Boyansko ezero str.",
                "09:30 - 18:00",
                10.,
                42.6446313,
                23.2661834,
                R.drawable.boyana_church
        ));
        seedPlaces.add(new Places(
                "Largo",
                "",
                "The Largo is an architectural ensemble of three Socialist Classicism edifices in central Sofia, the capital of Bulgaria, designed and built in the 1950s with the intention to become the city's new representative centre. Today it is regarded as one of the prime examples of Socialist Classicism architecture in Southeastern Europe, as well as one of the main landmarks of Sofia.\n" +
                        "\n" +
                        "The ensemble consists of the former Party House (former headquarters of the now defunct Bulgarian Communist Party), now used as administrative offices by the National Assembly of Bulgaria, in the centre, and two side edifices: one today accommodating the TZUM department store and the Council of Ministers of Bulgaria and another that is today occupied by the President's Office, the Sofia Hotel Balkan and the Ministry of Education.",
                "Nezavisimost square",
                "24/7",
                0.,
                42.697795,
                23.323083,
                R.drawable.largo
        ));
        seedPlaces.add(new Places(
                "Ivan Vazov National Theatre",
                "",
                "The Ivan Vazov National Theatre (Bulgarian: Народен театър „Иван Вазов“, Naroden teatar „Ivan Vazov“) is Bulgaria's national theatre, as well as the oldest and most authoritative theatre in the country and one of the important landmarks of Sofia, the capital of Bulgaria. It is located in the centre of the city, with the facade facing the City Garden.\n" +
                        "\n" +
                        "Founded in 1904 by the artists from the Salza i Smyah company, it was initially called simply the National Theatre, but before being named after the prominent writer Ivan Vazov it also bore the name of Krastyu Sarafov between 1952 and 1962. Incidentally Vazov's play, \"The Outcasts\" was the first to be performed at the theatre when it opened. The theatre's Neoclassical building, designed by famous Viennese theatre architects Hermann Helmer and Ferdinand Fellner, was finished in 1906 and opened on 3 January 1907. The building was extensively damaged by a fire in 1923 during an anniversary celebration, but was reconstructed in 1929 by German architect Martin Dülfer.\n" +
                        "\n" +
                        "A theatrical school was established as part of the National Theatre in 1925. The bombing of Sofia in World War II caused considerable damage to the building, but it was reconstructed in 1945. Another reconstruction followed in 1971–1975, and a €100,000 restoration project was implemented in 2006.\n" +
                        "\n" +
                        "The Ivan Vazov National Theatre has a well-equipped main stage with 750 seats, a smaller 120-seat stage and an additional 70-seat one on the fourth floor.\n" +
                        "\n" +
                        "The building's facade is depicted on the obverse of the Bulgarian 50 levs banknote, issued in 1999 and 2006.\n" +
                        "\n" +
                        "The theatre has been host to productions from notable theatre directors such as Alexander Morfov who has been the Chief director since 1993.",
                "5 Dyakon Igrantiy str.",
                "09:00 - 21:00",
                0.,
                42.6944476,
                23.3260587,
                R.drawable.theatre
        ));

        for (Places place: seedPlaces) {
            this.insert(place);
        }
    }
}
