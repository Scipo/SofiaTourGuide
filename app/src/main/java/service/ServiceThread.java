package service;

import android.content.Context;
import android.location.Location;

import java.util.List;

import DB.PlacesBaseHelper;
import DB.PlacesCRUD;
import model.PlaceLocation;
import model.Places;

/**
 * Created by Mitko on 7.2.2018 г..
 */

// DistanceService logic extracted on a separate thread
public class ServiceThread implements Runnable {
    private PlacesCRUD crud;
    private LocationService locationService;

    public ServiceThread(Context context) {
        this.crud = new PlacesCRUD(new PlacesBaseHelper(context));
        this.locationService = new LocationService(context);
    }

    private Location getCurrentLocation() {
        // TODO: да намира текущите географски координати използвайки GPS
        // https://stackoverflow.com/questions/1513485/how-do-i-get-the-current-gps-location-programmatically-in-android

        // throw new UnsupportedOperationException("TODO");
        return this.locationService.getLocation();
    }

    @Override
    public void run() {
        while (true) {
            List<Places> allPlaces = crud.GetAll();
            Location currentLocation = this.getCurrentLocation();
            Places nearestPlace = null;

            // TODO: на всеки 30 минути взима текущото местоположение и намира най-близкия обект от allPlaces (имат си и те lat, lon)
            // TODO: ако най-близкият обект е на по-малко от километър от нас, да създаде notification еди кво си е наблизо ходи го виж
            // сървиса съм го направил да се стартира заедно с приложението и да умира заедно с него така че можеш да си тестваш спокойно

            try {
                wait(30 * 60 * 1000);
                Double minDiff = Double.MAX_VALUE;

                for(Places place: allPlaces){
                    Double diff = Math.abs((place.getLat() + place.getLon()) -
                            (currentLocation.getLatitude() + currentLocation.getLongitude()));
                    if(minDiff > diff){
                        minDiff = diff;
                        nearestPlace = place;
                    }

                    if(nearestPlace != null){
                        // TODO Need to build the notification with the found place
                        // https://stackoverflow.com/questions/1207269/sending-a-notification-from-a-service-in-android
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}