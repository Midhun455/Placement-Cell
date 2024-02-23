package com.example.placementcell.COMMON;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.placementcell.R;
import com.example.placementcell.databinding.ViewUsersBinding;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<RequestPojo> {
    Context context;
    List<RequestPojo> requestPojoList;
    ViewUsersBinding binding;
    SharedPreferences preferences;
    String type;

    public StudentAdapter(Context context, List<RequestPojo> requestPojoList) {
        super(context, R.layout.view_users, requestPojoList);
        this.context = context;
        this.requestPojoList = requestPojoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        binding = ViewUsersBinding.inflate(LayoutInflater.from(getContext()), parent, false);

        preferences = getContext().getSharedPreferences("SharedData", Context.MODE_PRIVATE);
        type = preferences.getString("type", "");


        binding.name.setText(requestPojoList.get(position).getSname());
        binding.email.setText(requestPojoList.get(position).getSemail());
        binding.phone.setText(requestPojoList.get(position).getSphone());
        binding.dept.setText(requestPojoList.get(position).getDept());
        binding.gender.setText(requestPojoList.get(position).getGender());
        binding.dob.setText(requestPojoList.get(position).getDob());
        binding.address.setText(requestPojoList.get(position).getAddress());
        binding.college.setText(requestPojoList.get(position).getCollege());
        binding.degree.setText(requestPojoList.get(position).getDegree()+" %");
        binding.plustwo.setText(requestPojoList.get(position).getPlustwo()+" %");
        binding.tenth.setText(requestPojoList.get(position).getTenth()+" %");

        return binding.getRoot();
    }
}
