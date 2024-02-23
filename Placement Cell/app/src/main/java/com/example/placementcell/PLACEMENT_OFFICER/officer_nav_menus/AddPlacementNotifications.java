package com.example.placementcell.PLACEMENT_OFFICER.officer_nav_menus;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.placementcell.COMMON.RequestPojo;
import com.example.placementcell.COMMON.Utility;
import com.example.placementcell.R;
import com.example.placementcell.databinding.OfficerAddPlacementBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AddPlacementNotifications extends Fragment {

    private static final int REQUEST_SMS_PERMISSION = 123;
    String title, location, comp_name, comp_email, comp_url, job_type, vacancy, job_desc, placement_date, min_quali, degree, plustwo, tenth, backlogs;
    OfficerAddPlacementBinding binding;
    String[] courses = {"Qualification", "BSc", "MSc", "BBA", "MBA", "BA", "MA", "BCA", "MCA", "B.Tech", "M.Tech", "B.Com", "M.Com", "LLB", "LLM", "B.Ed", "M.Ed", "B.Arch", "M.Arch", "BHM", "MHM", "BFA", "MFA", "BJMC", "MJMC", "B.Des", "M.Des", "BBA-LLB", "BBM", "BMS", "MMS", "PGDM"};
    String[] jobTypes = {"Employment Type", "Full Time", "Part Time"};
    List<RequestPojo> requestPojoList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = OfficerAddPlacementBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, jobTypes) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (position == 0) {
                    Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.advenpro);
                    textView.setTypeface(typeface);
                    textView.setTextColor(Color.GRAY);
                    textView.setBackgroundColor(Color.LTGRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                    Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.advenpro);
                    textView.setTypeface(typeface);
                }
                return view;
            }
        };


        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerr.setAdapter(arrayAdapter);


        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, courses) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (position == 0) {
                    Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.advenpro);
                    textView.setTypeface(typeface);
                    textView.setTextColor(Color.GRAY);
                    textView.setBackgroundColor(Color.LTGRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                    Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.advenpro);
                    textView.setTypeface(typeface);
                }
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.quali.setAdapter(arrayAdapter1);


        final Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTimeInMillis(System.currentTimeMillis() - 1000);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                binding.placementDate.getEditText().setText(sdf.format(myCalendar.getTime()));
            }

        };

        binding.placementDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        return view;
    }

    private void validate() {
        title = binding.jobTitle.getEditText().getText().toString().trim();
        location = binding.jobLocation.getEditText().getText().toString().trim();
        comp_name = binding.compName.getEditText().getText().toString().trim();
        comp_email = binding.compEmail.getEditText().getText().toString().trim();
        job_type = binding.spinnerr.getSelectedItem().toString().trim();
        vacancy = binding.vacancies.getEditText().getText().toString().trim();
        job_desc = binding.jobDesc.getEditText().getText().toString().trim();
        placement_date = binding.placementDate.getEditText().getText().toString().trim();
        min_quali = binding.quali.getSelectedItem().toString().trim();
        degree = binding.college.getEditText().getText().toString().trim();
        plustwo = binding.plustwo.getEditText().getText().toString().trim();
        tenth = binding.tenth.getEditText().getText().toString().trim();
        backlogs = binding.backLogs.getEditText().getText().toString().trim();
        comp_url = binding.compUrl.getEditText().getText().toString().trim();

        String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

        if (title.isEmpty()) {
            binding.jobTitle.requestFocus();
            binding.jobTitle.setError("Enter Job Title");
        } else if (location.isEmpty()) {
            binding.jobTitle.setError(null);
            binding.jobLocation.requestFocus();
            binding.jobLocation.setError("Enter Job Location");
        } else if (comp_name.isEmpty()) {
            binding.jobLocation.setError(null);
            binding.compName.requestFocus();
            binding.compName.setError("Enter Company Name");
        } else if (!comp_email.matches(emailPattern)) {
            binding.compName.setError(null);
            binding.compEmail.requestFocus();
            binding.compEmail.setError("Enter Company Email");
        } else if (job_type.equals("Employment Type")) {
            binding.compEmail.setError(null);
            Snackbar.make(binding.getRoot(), "Enter Job Type", Snackbar.LENGTH_LONG).show();
        } else if (vacancy.isEmpty()) {
            binding.vacancies.requestFocus();
            binding.vacancies.setError("Enter No Of Vacancies");
        } else if (job_desc.isEmpty()) {
            binding.vacancies.setError(null);
            binding.jobDesc.requestFocus();
            binding.jobDesc.setError("Enter Job description");
        } else if (placement_date.isEmpty()) {
            binding.jobDesc.setError(null);
            binding.placementDate.requestFocus();
            binding.placementDate.setError("Enter Placement Drive Date");
        } else if (min_quali.equals("Qualification")) {
            binding.placementDate.setError(null);
            Snackbar.make(binding.getRoot(), "Choose Minimum Qualification", Snackbar.LENGTH_LONG).show();
        } else if (degree.isEmpty()) {
            binding.college.requestFocus();
            binding.college.setError("Enter Degree Marks");
        } else if (plustwo.isEmpty()) {
            binding.college.setError(null);
            binding.plustwo.requestFocus();
            binding.plustwo.setError("Enter Plus Two Marks");
        } else if (tenth.isEmpty()) {
            binding.plustwo.setError(null);
            binding.tenth.requestFocus();
            binding.tenth.setError("Enter High School Marks");
        } else if (backlogs.isEmpty()) {
            binding.tenth.setError(null);
            binding.backLogs.requestFocus();
            binding.backLogs.setError("Enter Backlogs");
        } else if (comp_url.isEmpty()) {
            binding.backLogs.setError(null);
            binding.compUrl.requestFocus();
            binding.compUrl.setError("Enter Website");
        } else {
            binding.compUrl.setError(null);
            addPlacement();
        }
    }

    public void addPlacement() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    //    sendSMS();
                    getNumber();
                    Toast.makeText(getContext(), "Placement Added!", Toast.LENGTH_LONG).show();
                    AddPlacementNotifications fragment = new AddPlacementNotifications();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, fragment);
                    transaction.commit();
                } else {
                    Toast.makeText(getContext(), "Can't Add Placement", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("u_id", "No logid");
                map.put("key", "addPlacement");
                map.put("officer_id", uid);
                map.put("job_title", title);
                map.put("job_location", location);
                map.put("comp_name", comp_name);
                map.put("comp_email", comp_email);
                map.put("job_type", job_type);
                map.put("vacancies", vacancy);
                map.put("job_desc", job_desc);
                map.put("placement_date", placement_date);
                map.put("min_qualification", min_quali);
                map.put("college", degree);
                map.put("plustwo", plustwo);
                map.put("tenth", tenth);
                map.put("backLogs", backlogs);
                map.put("comp_url", comp_url);
                return map;
            }
        };
        queue.add(request);
    }


    public void getNumber() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    Gson gson = new Gson();
                    requestPojoList = Arrays.asList(gson.fromJson(response, RequestPojo[].class));
                    System.out.println("1 position :" + requestPojoList.get(1).getSphone());
                    for (int i = 0; i < requestPojoList.size(); i++) {
                        String Num = requestPojoList.get(i).getSphone();
                        System.out.println("PHONE :" + Num);
                        String msg = "Job Notification 🔔\n\nJob Title:" + title + "\nJob Location: " + location + "\nCompany Name: " + comp_name + "\nCompany Email: " + comp_email + "\nJob Type: " + job_type + "\nVacancies: " + vacancy + "\nJob Description: " + job_desc + "\nPlacement Date: " + placement_date + "\nQualification: " + min_quali + "\nWebsite :" + comp_url;
                        System.out.println("msg");

                        SmsManager smsManager = SmsManager.getDefault();
                        ArrayList<String> parts = smsManager.divideMessage(msg);
                        int numParts = parts.size();

                        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>(numParts);
                        ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>(numParts);

                        for (int j = 0; j < numParts; j++) {
                            Intent sentIntent = new Intent("SMS_SENT");
                            Intent deliveryIntent = new Intent("SMS_DELIVERED");
                            sentIntents.add(PendingIntent.getBroadcast(getContext(), 0, sentIntent, PendingIntent.FLAG_IMMUTABLE));
                            deliveryIntents.add(PendingIntent.getBroadcast(getContext(), 0, deliveryIntent, PendingIntent.FLAG_IMMUTABLE));
                        }
                        smsManager.sendMultipartTextMessage(Num, null, parts, sentIntents, deliveryIntents);
                    }
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
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "getNumber");
                return map;
            }
        };
        queue.add(request);
    }
}