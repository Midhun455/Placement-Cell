package com.example.placementcell.PLACEMENT_OFFICER;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.placementcell.COMMON.Utility;
import com.example.placementcell.LoginActivity;
import com.example.placementcell.RegistrationActivity;
import com.example.placementcell.databinding.ActivityOfficerRegistrationBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OfficerRegistration extends AppCompatActivity {
    String emailPattern, o_name, o_age, o_address, o_phone, o_email, o_pswd, o_dateOfjoin, o_quali, college_name;
    ActivityOfficerRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOfficerRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm");
        o_dateOfjoin = ft.format(dNow);

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    @Override
    public void onBackPressed() {
//        Toast.makeText(this, "Please Logout", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
        finish();
    }

    private void validate() {
        o_name = binding.officerName.getEditText().getText().toString();
        o_address = binding.officerAddress.getEditText().getText().toString();
        o_phone = binding.officerPhone.getEditText().getText().toString();
        o_email = binding.officerMail.getEditText().getText().toString();
        o_pswd = binding.officerPass.getEditText().getText().toString();
        o_quali = binding.officerQuali.getEditText().getText().toString();
        college_name = binding.collegeName.getEditText().getText().toString();

        if (o_name.isEmpty()) {
            binding.officerName.requestFocus();
            binding.officerName.setError("Enter Name");
        } else if (!o_email.matches(emailPattern)) {
            binding.officerName.setError(null);
            binding.officerMail.requestFocus();
            binding.officerMail.setError("Enter Valid Email Address");
        } else if (o_phone.length() != 10) {
            binding.officerMail.setError(null);
            binding.officerPhone.requestFocus();
            binding.officerPhone.setError("Enter Valid Number");
        } else if (o_quali.isEmpty()) {
            binding.officerPhone.setError(null);
            binding.officerQuali.requestFocus();
            binding.officerQuali.setError("Enter Your Qualification");
        } else if (college_name.isEmpty()) {
            binding.officerQuali.setError(null);
            binding.collegeName.requestFocus();
            binding.collegeName.setError("Enter College Name");
        } else if (o_address.isEmpty()) {
            binding.collegeName.setError(null);
            binding.officerAddress.requestFocus();
            binding.officerAddress.setError("Enter Address");
        } else if (o_pswd.isEmpty()) {
            binding.officerAddress.setError(null);
            binding.officerPass.requestFocus();
            binding.officerPass.setError("Enter Password");
        } else {
            binding.officerPass.setError(null);
            officerRegistration();
        }
    }

    private void officerRegistration() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (response.trim().equals("Already Exist")) {
                    Toast.makeText(getApplicationContext(), "Account is already Exist", Toast.LENGTH_SHORT).show();
                } else if (!response.trim().equals("failed")) {
                    Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
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
                map.put("key", "officer_register");
                map.put("o_name", o_name);
                map.put("o_quali", o_quali);
                map.put("o_address", o_address);
                map.put("o_phone", o_phone);
                map.put("o_email", o_email);
                map.put("o_pswd", o_pswd);
                map.put("college_name", college_name);

                return map;
            }
        };
        queue.add(request);
    }

}

