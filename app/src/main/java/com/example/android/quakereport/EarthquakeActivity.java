/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private ListView rootView;

    private String url = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=1&limit=50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        // Create a fake list of earthquake locations.
        task.execute(url);




    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<EarthQuake> >{

        @Override
        protected ArrayList<EarthQuake> doInBackground(String... params) {

            if (params.length < 1 || params[0] == null){
                return null;
            }

            URL url = null;
            try{
                url = new URL(params[0]);
            }catch(MalformedURLException e){
                Log.e(LOG_TAG, "Error with creating URL ", e);
            }

            if (url == null)
                return null;

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            String jsonResponse = "";

            try{
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = QueryUtils.readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }catch(IOException e){
                Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            }
            return QueryUtils.extractEarthquakes(jsonResponse);
        }


        protected void onPostExecute(final ArrayList<EarthQuake> result) {

            if (result == null || result.isEmpty() )
                return;

            updateUI(result);
        }

        private void updateUI(final ArrayList<EarthQuake> result) {
            EarthQuakeAdapter adapter = new EarthQuakeAdapter(EarthquakeActivity.this, result);

            rootView = (ListView) findViewById(R.id.list);

            rootView.setAdapter(adapter);

            rootView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    EarthQuake quake = result.get(i);

                    Uri webpage = Uri.parse(quake.getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
