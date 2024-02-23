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

public class ExamAdapter extends ArrayAdapter<RequestPojo> {
    Activity context;
    List<RequestPojo> rest_List;

    public ExamAdapter(Activity context, List<RequestPojo> rest_List) {
        super(context, R.layout.view_exam_adaper, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        final View view = inflater.inflate(R.layout.view_exam_adaper, null, true);

        TextView exam_name = (TextView) view.findViewById(R.id.exam_name);
        TextView exam_desc = (TextView) view.findViewById(R.id.exam_desc);
        TextView exam_date = (TextView) view.findViewById(R.id.exam_date);
        TextView exam_dept = (TextView) view.findViewById(R.id.exam_dept);


        return view;
    }
}
