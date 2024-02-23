package com.example.placementcell.COMMON;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.placementcell.R;

import java.util.List;

public class EventsAdapter extends ArrayAdapter<RequestPojo> {
    Activity context;
    List<RequestPojo> rest_List;

    public EventsAdapter(Activity context, List<RequestPojo> rest_List) {
        super(context, R.layout.view_event_adaper, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        final View view = inflater.inflate(R.layout.view_event_adaper, null, true);

        TextView event_name = (TextView) view.findViewById(R.id.event_name);
        TextView event_desc = (TextView) view.findViewById(R.id.event_desc);
        TextView event_date = (TextView) view.findViewById(R.id.event_date);
        TextView event_time = (TextView) view.findViewById(R.id.event_time);

        event_name.setText(rest_List.get(position).getSubject());
        event_date.setText(rest_List.get(position).getEvent_date());
        event_time.setText(rest_List.get(position).getEvent_time());
        event_desc.setText(rest_List.get(position).getDesc());
        return view;
    }
}
