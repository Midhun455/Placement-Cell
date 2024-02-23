package com.example.placementcell.PLACEMENT_OFFICER.officer_nav_menus;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.placementcell.COMMON.PlacementAdapter;
import com.example.placementcell.COMMON.RequestPojo;
import com.example.placementcell.COMMON.Utility;
import com.example.placementcell.STUDENT.TestActivity;
import com.example.placementcell.databinding.FragmentViewPlacementsBinding;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPlacements extends Fragment {
    List<RequestPojo> requestPojoList;
    FragmentViewPlacementsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentViewPlacementsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
        final String type = prefs.getString("type", "No logid");
        if (!type.equals("ADMIN")) {
            binding.placementList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Ready to start exam..?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String id = requestPojoList.get(position).getPl_id();
                            String name = requestPojoList.get(position).getComp_name();
                            String jobName = requestPojoList.get(position).getTitle();
                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("SharedData", MODE_PRIVATE).edit();
                            editor.putString("pl_id", "" + id);
                            editor.putString("name", "" + name);
                            editor.putString("jobName", "" + jobName);
                            editor.apply();
                            Intent intent = new Intent(getActivity(), TestActivity.class);
                            startActivity(intent);
                        }
                    }).setNeutralButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.setTitle("PlacementCell");
                    alert.show();
                    alert.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#000000"));
                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#000000"));
                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00FF00"));
                }
            });
        }
        getPlacements();
        return view;
    }

    public void getPlacements() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    Gson gson = new Gson();
                    requestPojoList = Arrays.asList(gson.fromJson(response, RequestPojo[].class));

                    PlacementAdapter adapter = new PlacementAdapter(getActivity(), requestPojoList);
                    binding.placementList.setAdapter(adapter);
                    registerForContextMenu(binding.placementList);
                } else {
                    Toast.makeText(getContext(), "No Data", Toast.LENGTH_LONG).show();
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
                final String type = prefs.getString("type", "No logid");//"No name defined" is the default value.
                Map<String, String> map = new HashMap<String, String>();
                System.out.println("HHHHHHHHH" + uid);
                if (type.equals("OFFICER")) {
                    map.put("key", "getPlacementsOfficer");
                    map.put("officer_id", uid);
                } else {
                    map.put("key", "getPlacementsStudent");
                    map.put("s_id", uid);
                }
                return map;
            }
        };
        queue.add(request);
    }
}