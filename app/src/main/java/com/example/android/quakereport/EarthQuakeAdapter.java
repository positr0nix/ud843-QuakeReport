package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

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



        TextView quakeMagn = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView quakeCity = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView quakeDate = (TextView) listItemView.findViewById(R.id.date);
        TextView quakeOffset = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView quakeTime = (TextView) listItemView.findViewById(R.id.time);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) quakeMagn.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        quakeCity.setText( currentQuake.getCity() );
        quakeMagn.setText( currentQuake.getMagnitudeStr() );
        quakeDate.setText( currentQuake.getDate() );
        quakeOffset.setText( currentQuake.getKmOffset() );
        quakeTime.setText( currentQuake.getTime() );

        return listItemView;
    }

    public int getMagnitudeColor(Double magnitude) {
        int idColor = 0;
        switch ( magnitude.intValue() ) {
            case 10:
                idColor = R.color.magnitude10plus;
                break;
            case 9:
                idColor = R.color.magnitude9;
                break;
            case 8:
                idColor = R.color.magnitude8;
                break;
            case 7:
                idColor = R.color.magnitude7;
                break;
            case 6:
                idColor = R.color.magnitude6;
                break;
            case 5:
                idColor = R.color.magnitude5;
                break;
            case 4:
                idColor = R.color.magnitude4;
                break;
            case 3:
                idColor = R.color.magnitude3;
                break;
            case 2:
                idColor = R.color.magnitude2;
                break;
            default:
                idColor = R.color.magnitude1;
                break;
        }
        return ContextCompat.getColor(getContext(), idColor);
    }
}
