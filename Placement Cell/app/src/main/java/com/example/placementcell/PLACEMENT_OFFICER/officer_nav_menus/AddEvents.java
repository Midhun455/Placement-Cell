package com.example.placementcell.PLACEMENT_OFFICER.officer_nav_menus;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.placementcell.COMMON.Utility;
import com.example.placementcell.databinding.FragmentAddEventsBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddEvents extends Fragment {
    FragmentAddEventsBinding binding;
    String subject, desc, date, time;
    TimePickerDialog pickerq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddEventsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        // getPushMsgData();

        final Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTimeInMillis(System.currentTimeMillis() - 1000);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                binding.eventDate.getEditText().setText(sdf.format(myCalendar.getTime()));
            }
        };


        binding.eventDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        binding.eventTime.getEditText().setInputType(InputType.TYPE_NULL);
        binding.eventTime.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                //time picker dialog
                pickerq = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                binding.eventTime.getEditText().setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                pickerq.show();
            }
        });


        return view;
    }

    private void validate() {
        subject = binding.eventTitle.getEditText().getText().toString().trim();
        desc = binding.eventDesc.getEditText().getText().toString().trim();
        date = binding.eventDate.getEditText().getText().toString().trim();
        time = binding.eventTime.getEditText().getText().toString().trim();

        if (subject.isEmpty()) {
            binding.eventTitle.requestFocus();
            binding.eventTitle.setError("Enter Subject");
        } else if (desc.isEmpty()) {
            binding.eventTitle.setError(null);
            binding.eventDesc.requestFocus();
            binding.eventDesc.setError("Enter Event Description");
        } else if (date.isEmpty()) {
            binding.eventDesc.setError(null);
            binding.eventDate.requestFocus();
            binding.eventDate.setError("Enter Date");
        } else if (time.isEmpty()) {
            binding.eventDate.setError(null);
            binding.eventTime.requestFocus();
            binding.eventTime.setError("Enter Time");
        } else {
            addEvents();
        }
    }


    public void getPushMsgData() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    String data = response;
                    String[] respArr = data.trim().split("#");
                    for (String GET : respArr) {
                        System.out.println("PHONE  : " + GET);
                        sendSMS(GET);
                    }
                } else {
                    Toast.makeText(getContext(), "No Messages", Toast.LENGTH_LONG).show();
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
                map.put("key", "getPhoneNumberOut");
                return map;
            }
        };
        queue.add(request);
    }

    private void sendSMS(String get) {
        String msg = "Subject :" + subject + "\nDescription :" + desc + "\nDate :" + date + "\nTime :" + time;
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
        smsManager.sendMultipartTextMessage(get, null, parts, sentIntents, deliveryIntents);
    }


    private void addEvents() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    getPushMsgData();
                    Toast.makeText(getContext(), "Event Added!", Toast.LENGTH_LONG).show();
//                    getActivity().recreate();
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
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", Context.MODE_PRIVATE);
                final String uid = prefs.getString("u_id", "No logid");
                map.put("key", "addEvent");
                map.put("officer_id", uid);
                map.put("subject", subject);
                map.put("event_desc", desc);
                map.put("event_date", date);
                map.put("event_time", time);
                return map;
            }
        };
        queue.add(request);
    }
}