package com.example.android.quakereport;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

/**
 * Created by veureka on 9/7/16.
 */
public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<EarthQuake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<EarthQuake> loadInBackground() {

        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";

        try{
            url = new URL(this.mUrl);
        }catch(MalformedURLException e){
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        if (url == null)
            return null;

        String jsonData = QueryUtils.makeConnection(url);
        ArrayList<EarthQuake> quakes = QueryUtils.extractEarthquakes(jsonData);
        Log.e("Load In Background ==> " , jsonData);
        return quakes;
    }
}
