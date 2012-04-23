package com.scottbyrns.utilties;

/**
 * Example entity for testing the JSON object mapper.
 */
public class GeoLocation {
    private double latitude;
    private double longitude;

    public void setLatitude (double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude () {
        return latitude;
    }

    public void setLongitude (double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude () {
        return longitude;
    }
}