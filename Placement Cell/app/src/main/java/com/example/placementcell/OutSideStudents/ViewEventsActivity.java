package com.example.placementcell.OutSideStudents;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.placementcell.COMMON.EventsAdapter;
import com.example.placementcell.COMMON.RequestPojo;
import com.example.placementcell.COMMON.Utility;
import com.example.placementcell.databinding.ActivityViewEventsBinding;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewEventsActivity extends AppCompatActivity {
    ActivityViewEventsBinding binding;
    List<RequestPojo> requestPojoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewEventsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getEvents();
    }

    public void getEvents() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    Gson gson = new Gson();
                    requestPojoList = Arrays.asList(gson.fromJson(response, RequestPojo[].class));

                    EventsAdapter adapter = new EventsAdapter(ViewEventsActivity.this, requestPojoList);
                    binding.eventsList.setAdapter(adapter);
                    registerForContextMenu(binding.eventsList);
                } else {
                    Toast.makeText(getApplicationContext(), "No Events", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "getEventsStudent");
                return map;
            }
        };
        queue.add(request);
    }
}