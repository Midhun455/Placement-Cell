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

public class PlacementAdapter extends ArrayAdapter<RequestPojo> {
    Activity context;
    List<RequestPojo> requestPojoList;

    public PlacementAdapter(Activity context, List<RequestPojo> requestPojoList) {
        super(context, R.layout.view_placements_adapter, requestPojoList);
        this.context = context;
        this.requestPojoList = requestPojoList;
        // TODO Auto-generated constructor stub
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        final View view = inflater.inflate(R.layout.view_placements_adapter, null, true);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView location = (TextView) view.findViewById(R.id.location);
        TextView comp_name = (TextView) view.findViewById(R.id.comp_name);
        TextView comp_email = (TextView) view.findViewById(R.id.comp_email);
        TextView job_type = (TextView) view.findViewById(R.id.type);
        TextView vacancies = (TextView) view.findViewById(R.id.vacancies);
        TextView quali = (TextView) view.findViewById(R.id.quali);
        TextView job_desc = (TextView) view.findViewById(R.id.job_desc);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView post_date = (TextView) view.findViewById(R.id.post_date);
        TextView compurl = view.findViewById(R.id.urll);

        title.setText(requestPojoList.get(position).getTitle());
        location.setText(requestPojoList.get(position).getLocation());
        comp_name.setText(requestPojoList.get(position).getComp_name());
        comp_email.setText(requestPojoList.get(position).getComp_email());
        job_type.setText(requestPojoList.get(position).getJobtype());
        vacancies.setText(requestPojoList.get(position).getVacancies());
        quali.setText(requestPojoList.get(position).getMin_quali());
        job_desc.setText(requestPojoList.get(position).getJob_desc());
        date.setText(requestPojoList.get(position).getDate());
        post_date.setText(requestPojoList.get(position).getPosted_date());
//        compurl.setText(requestPojoList.get(position).getUrl());


        return view;
    }
}
