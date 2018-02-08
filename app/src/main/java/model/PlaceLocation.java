package model;

/**
 * Created by Mitko on 7.2.2018 г..
 */

public class PlaceLocation {
    private String latitude;
    private String longitude;

    public PlaceLocation(String lat, String lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}