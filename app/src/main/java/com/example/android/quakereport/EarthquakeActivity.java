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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private ListView rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<EarthQuake> earthQuakes = new ArrayList<EarthQuake>();
        earthQuakes.add( new EarthQuake("San Francisco", 7.2, "Feb 2, 2016") );
        earthQuakes.add( new EarthQuake("London", 6.1, "July 20, 2015") );
        earthQuakes.add( new EarthQuake("Tokyo", 3.9, "Nov 10, 2014") );
        earthQuakes.add( new EarthQuake("Mexico city", 5.4, "May 3, 2014") );
        earthQuakes.add( new EarthQuake("Moscow", 2.8, "Jan 31, 2013") );
        earthQuakes.add( new EarthQuake("Rio de Janeiro", 4.9, "Aug 19, 2012") );
        earthQuakes.add( new EarthQuake("Paris", 1.6, "Oct 30, 2011") );


        EarthQuakeAdapter adapter = new EarthQuakeAdapter(this, earthQuakes);

        rootView = (ListView) findViewById(R.id.list);

        rootView.setAdapter(adapter);


    }
}
