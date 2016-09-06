package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by veureka on 9/5/16.
 */
public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {



    public EarthQuakeAdapter(Context context, ArrayList<EarthQuake> quakes) {
        super(context,0, quakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_quakes, parent, false);
        }

        EarthQuake currentQuake = getItem(position);

        TextView quakeMagn = (TextView) listItemView.findViewById(R.id.quake_magn);
        TextView quakeCity = (TextView) listItemView.findViewById(R.id.quake_city);
        TextView quakeDate = (TextView) listItemView.findViewById(R.id.quake_date);
        TextView quakeOffset = (TextView) listItemView.findViewById(R.id.offset_city);
        TextView quakeTime = (TextView) listItemView.findViewById(R.id.quake_time);

        quakeCity.setText( currentQuake.getCity() );
        quakeMagn.setText( currentQuake.getMagnitude() );
        quakeDate.setText( currentQuake.getDate() );
        quakeOffset.setText( currentQuake.getKmOffset() );
        quakeTime.setText( currentQuake.getTime() );

        return listItemView;
    }
}
