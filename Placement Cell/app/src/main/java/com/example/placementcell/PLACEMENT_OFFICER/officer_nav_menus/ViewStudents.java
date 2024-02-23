package com.example.placementcell.PLACEMENT_OFFICER.officer_nav_menus;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.placementcell.COMMON.RequestPojo;
import com.example.placementcell.COMMON.StudentAdapter;
import com.example.placementcell.COMMON.Utility;
import com.example.placementcell.databinding.FragmentViewStudentsBinding;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewStudents extends Fragment {
    FragmentViewStudentsBinding binding;
    List<RequestPojo> requestPojoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentViewStudentsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        getStudents();
        return view;
    }

    public void getStudents() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    Gson gson = new Gson();
                    requestPojoList = Arrays.asList(gson.fromJson(response, RequestPojo[].class));

                    StudentAdapter adapter = new StudentAdapter(getActivity(), requestPojoList);
                    binding.studentList.setAdapter(adapter);
                    registerForContextMenu(binding.studentList);
                } else {
                    Toast.makeText(getContext(), "No Events", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("u_id", "No logid");//"No name defined" is the default value.
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "viewStudentsCommon");
                return map;
            }
        };
        queue.add(request);
    }
}