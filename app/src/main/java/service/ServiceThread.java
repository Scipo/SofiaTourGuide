package service;

import android.content.Context;

import java.util.List;

import DB.PlacesBaseHelper;
import DB.PlacesCRUD;
import model.Places;

/**
 * Created by Mitko on 7.2.2018 г..
 */

// DistanceService logic extracted on a separate thread
public class ServiceThread implements Runnable {
    private PlacesCRUD crud;

    public ServiceThread(Context context) {
        this.crud = new PlacesCRUD(new PlacesBaseHelper(context));
    }

    private Location getCurrentLocation() {
        // TODO: да намира текущите географски координати използвайки GPS
        // https://stackoverflow.com/questions/1513485/how-do-i-get-the-current-gps-location-programmatically-in-android
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void run() {
        while (true) {
            List<Places> allPlaces = crud.GetAll();
            Location currentLocation = this.getCurrentLocation();

            // TODO: на всеки 30 минути взима текущото местоположение и намира най-близкия обект от allPlaces (имат си и те lat, lon)
            // TODO: ако най-близкият обект е на по-малко от километър от нас, да създаде notification еди кво си е наблизо ходи го виж
            // сървиса съм го направил да се стартира заедно с приложението и да умира заедно с него така че можеш да си тестваш спокойно

            try {
                wait(30 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}