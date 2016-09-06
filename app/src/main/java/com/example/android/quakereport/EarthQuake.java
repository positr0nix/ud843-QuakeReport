package com.example.android.quakereport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by veureka on 9/5/16.
 */
public class EarthQuake {
    private String date;
    private String time;
    private String kmOffset;
    private String city;
    private String url;
    private Double magnitude;

    private DecimalFormat formatter = new DecimalFormat("0.0");
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
    private SimpleDateFormat timeFormatter = new SimpleDateFormat(" HH:mm a");

    /*
     * Constructor
     */
    public EarthQuake( String city, Double magnitude, Long unixtime, String url ) {
        setCity(city);
        setMagnitude(magnitude);
        setDate(unixtime);
        setUrl(url);
    }

    /*
     * set and get for DATE
     */
    public void setDate(Long unixtime) {
        Date dateObject = new Date(unixtime);

        this.date = dateFormatter.format(dateObject);
        this.time = timeFormatter.format(dateObject);
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    /*
     * set and get for City
     */
    public void setCity(String city) {
        int index = city.indexOf("of");
        int lenght = city.length();

        if (index < 0) {
            this.kmOffset = "Near the";
            this.city = city;
        }
        else {
            index+=2; //to get after "of" string
            this.kmOffset = city.substring(0, index);
            this.city = city.substring(index);
        }

    }

    public String getCity() {
        return this.city;
    }

    public String getKmOffset() { return this.kmOffset; }

    /*
     * set and get for Magnitude
     */
    public void setMagnitude(Double magnitude) {
        this.magnitude =  magnitude;
    }

    public String getMagnitudeStr() {
        return formatter.format(magnitude);
    }

    public Double getMagnitude() {
        return magnitude;
    }

    /*
     * set and get for URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
