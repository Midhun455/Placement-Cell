package com.example.placementcell;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.placementcell.COMMON.Utility;
import com.example.placementcell.databinding.ActivityStudentExternalRegistrationBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StudentExternalRegistration extends AppCompatActivity {
    ActivityStudentExternalRegistrationBinding binding;
    String[] genderArray = {"Select Gender", "Male", "Female"};
    String[] courses = {"Department", "BSc", "MSc", "BBA", "MBA", "BA", "MA", "BCA", "MCA", "B.Tech", "M.Tech", "B.Com", "M.Com", "LLB", "LLM", "B.Ed", "M.Ed", "B.Arch", "M.Arch", "BHM", "MHM", "BFA", "MFA", "BJMC", "MJMC", "B.Des", "M.Des", "BBA-LLB", "BBM", "BMS", "MMS", "PGDM"};

    String name, email, phone, gender, dob, dept, college, address, password, degree, plustwo, tenth,backlogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentExternalRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderArray) {
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
                    Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.advenpro);
                    textView.setTypeface(typeface);
                    textView.setTextColor(Color.GRAY);
                    textView.setBackgroundColor(Color.LTGRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                    Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.advenpro);
                    textView.setTypeface(typeface);
                }
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerr.setAdapter(arrayAdapter);


        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, courses) {
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
                    Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.advenpro);
                    textView.setTypeface(typeface);
                    textView.setTextColor(Color.GRAY);
                    textView.setBackgroundColor(Color.LTGRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                    Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.advenpro);
                    textView.setTypeface(typeface);
                }
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.studentDept.setAdapter(arrayAdapter1);


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

                binding.dob.getEditText().setText(sdf.format(myCalendar.getTime()));
            }

        };

        binding.dob.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(StudentExternalRegistration.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        name = binding.studentName.getEditText().getText().toString().trim();
        email = binding.studentMail.getEditText().getText().toString().trim();
        phone = binding.studentPhone.getEditText().getText().toString().trim();
        gender = binding.spinnerr.getSelectedItem().toString().trim();
        dob = binding.dob.getEditText().getText().toString().trim();
        dept = binding.studentDept.getSelectedItem().toString().trim();
        college = binding.collegeName.getEditText().getText().toString().trim();
        address = binding.studentAddress.getEditText().getText().toString().trim();
        password = binding.studentPass.getEditText().getText().toString().trim();
        degree = binding.degree.getEditText().getText().toString().trim();
        plustwo = binding.plustwo.getEditText().getText().toString().trim();
        tenth = binding.tenth.getEditText().getText().toString().trim();
        backlogs = binding.backLogs.getEditText().getText().toString().trim();

        String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

        if (name.isEmpty()) {
            binding.studentName.requestFocus();
            binding.studentName.setError("Enter Name");
        } else if (!email.matches(emailPattern)) {
            binding.studentName.setError(null);
            binding.studentMail.requestFocus();
            binding.studentMail.setError("Enter E-Mail");
        } else if (phone.length() != 10) {
            binding.studentMail.setError(null);
            binding.studentPhone.requestFocus();
            binding.studentPhone.setError("Enter Valid Mobile Number");
        } else if (gender.equals("Select Gender")) {
            binding.studentPhone.setError(null);
            Snackbar.make(binding.getRoot(), "Select Gender", Snackbar.LENGTH_LONG).show();
        } else if (dob.isEmpty()) {
            binding.dob.requestFocus();
            binding.dob.setError("Enter Date Of Birth");
        } else if (dept.isEmpty()) {
            binding.dob.setError(null);
            binding.studentDept.requestFocus();
            Snackbar.make(binding.getRoot(), "Enter Department", Snackbar.LENGTH_LONG).show();
        } else if (college.isEmpty()) {
            binding.collegeName.requestFocus();
            binding.collegeName.setError("Enter College Name");
        } else if (address.isEmpty()) {
            binding.collegeName.setError(null);
            binding.studentAddress.requestFocus();
            binding.studentAddress.setError("Enter Address");
        } else if (password.isEmpty()) {
            binding.studentAddress.setError(null);
            binding.studentPass.requestFocus();
            binding.studentPass.setError("Enter Password");
        } else if (degree.isEmpty()) {
            binding.studentPass.setError(null);
            binding.degree.requestFocus();
            binding.degree.setError("Enter Degree Marks");
        } else if (plustwo.isEmpty()) {
            binding.degree.setError(null);
            binding.plustwo.requestFocus();
            binding.plustwo.setError("Enter Plus Two Marks");
        } else if (tenth.isEmpty()) {
            binding.plustwo.setError(null);
            binding.tenth.requestFocus();
            binding.tenth.setError("Enter Tenth Marks");
        } else if (backlogs.isEmpty()) {
            binding.tenth.setError(null);
            binding.backLogs.requestFocus();
            binding.backLogs.setError("Enter Backlogs(Type 0 if not)");
        } else {
            binding.backLogs.setError(null);
            regStudent();
        }
    }

    private void regStudent() {
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
                map.put("key", "externalStudentReg");
                map.put("name", name);
                map.put("email", email);
                map.put("phone", phone);
                map.put("gender", gender);
                map.put("dob", dob);
                map.put("dept", dept);
                map.put("college", college);
                map.put("address", address);
                map.put("password", password);
                map.put("degree", degree);
                map.put("plustwo", plustwo);
                map.put("tenth", tenth);
                map.put("backlogs", backlogs);
                return map;
                //    String name, email, phone, gender, dob, dept, college, address, password, degree, plustwo, tenth;
            }
        };
        queue.add(request);
    }
}