package com.example.android.quakereport;

/**
 * Created by veureka on 9/5/16.
 */
public class EarthQuake {
    private String date;
    private String city;
    private Double magnitude;

    /*
     * Constructor
     */
    public EarthQuake( String city, Double magnitude, String date ) {
        setCity(city);
        setMagnitude(magnitude);
        setDate(date);
    }

    /*
     * set and get for DATE
     */
    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    /*
     * set and get for City
     */
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    /*
     * set and get for Magnitude
     */
    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public Double getMagnitude() {
        return this.magnitude;
    }
}
