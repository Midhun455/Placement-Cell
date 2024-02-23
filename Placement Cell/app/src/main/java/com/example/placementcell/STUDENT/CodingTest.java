package com.example.placementcell.STUDENT;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.placementcell.COMMON.Utility;
import com.example.placementcell.databinding.ActivityCodingTestBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CodingTest extends AppCompatActivity {
    public static int marks = 0, correct = 0, wrong = 0;
    ActivityCodingTestBinding binding;
    String answerText, aptitude, technical, numerical, comp_name, jobName;
    int flag = 0, count = 1, total = 0;
    SharedPreferences preferences, aptitudePref, codingPref, numericalPref, technicalPref;
    String[] codingQuestions = {
            "What is output of the below program?\n" +
                    "\n" +
                    "int main()\n" +
                    "{\n" +
                    " int i;\n" +
                    " for(i=0; i<5; ++i++)\n" +
                    " {\n" +
                    "   printf(\"Hello\");\n" +
                    " }\n" +
                    " return 0;\n" +
                    "}",
            "What is the meaning of below lines?\n" +
                    "void sum (int, int);",
            "What is output of below program?\n" +
                    "int main()\n" +
                    "{\n" +
                    "const int a = 10;\n" +
                    "printf(\"%d\",++a);\n" +
                    "return 0;\n" +
                    "}",
            "Can we declare function inside structure of C Programming?",
            "Which of the following is executed by Preprocessor?",
            "What will be the output of the following Java code?\n" +
                    "\n" +
                    "    class increment {\n" +
                    "        public static void main(String args[]) \n" +
                    "        {        \n" +
                    "             int g = 3;\n" +
                    "             System.out.print(++g * 8);\n" +
                    "        } \n" +
                    "    }",
            "What is the output of the below Java program?\npublic class TestingMethods5\n" +
                    "{\n" +
                    "  public static void main(String[] args)\n" +
                    "  {\n" +
                    "    int localVariable;\n" +
                    "    System.out.println(localVariable);\n" +
                    "  }\n" +
                    "}",
            "What will be the output of the following Java program?\n" +
                    "\n" +
                    "    class Output \n" +
                    "    {\n" +
                    "        public static void main(String args[])\n" +
                    "        {\n" +
                    "            int arr[] = {1, 2, 3, 4, 5};\n" +
                    "            for ( int i = 0; i < arr.length - 2; ++i)\n" +
                    "                System.out.println(arr[i] + \" \");\n" +
                    "        } \n" +
                    "    }",
            "What will be the output of the following Java code?\n" +
                    "\n" +
                    "    class String_demo \n" +
                    "    {\n" +
                    "        public static void main(String args[])\n" +
                    "        {\n" +
                    "            char chars[] = {'a', 'b', 'c'};\n" +
                    "            String s = new String(chars);\n" +
                    "            System.out.println(s);\n" +
                    "        }\n" +
                    "   }",
            " Which of the following is a superclass of every class in Java?",
    };

    String[] codingOptions = {
            "Hello is printed 5 times", "Compilation Error", "Hello is printed 2 times", "Hello is printed 3 times",
            "sum is function which takes int arguments", "Sum is a function which takes two int arguments and returns void", "it will produce compilation error", "Can't comment",
            "11", "10", "Compilation Error", "0",
            "Yes", "No", "Depends on Compiler", "Yes but run time error",
            "#include<stdio.h>", "return 0", "void main(int argc , char ** argv)", "None of the above",
            "32", "33", "24", "25",
            "0", "garbage value", "NullPointerException", "Compiler error",
            "1 2 3 4 5", "1 2 3 4", "1 2", "1 2 3",
            "abc", "a", "b", "c",
            "ArrayList", "Abstract class", "Object Class", "String",
    };

    String[] codingAnswers = {
            "Compilation Error",
            "Sum is a function which takes two int arguments and returns void",
            "Compilation Error",
            "No",
            "#include<stdio.h>",
            "32",
            "Compiler error",
            "1 2 3",
            "abc",
            "Object Class",

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCodingTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//getting Marks
        aptitudePref = getSharedPreferences("AptitudeData", MODE_PRIVATE);
        numericalPref = getSharedPreferences("NumericalData", MODE_PRIVATE);
//        codingPref = getSharedPreferences("Codi", MODE_PRIVATE);
        technicalPref = getSharedPreferences("TechnicalData", MODE_PRIVATE);


        //getvalue
        aptitude = aptitudePref.getString("aptitude", "");
        numerical = numericalPref.getString("numerical", "");
        technical = technicalPref.getString("technical", "");

        System.out.println(aptitude + "AP" + numerical + "NUM" + technical);

//CompanyDetails
        preferences = getSharedPreferences("SharedData", MODE_PRIVATE);
        jobName = preferences.getString("jobName", "");
        comp_name = preferences.getString("name", "");
        System.out.println(aptitude);
        System.out.println(numerical);
        System.out.println(technical);
        System.out.println(jobName);
        System.out.println(comp_name);
        binding.testName.setText("Coding Round: " + count);


        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {

                // Used for formatting digit to be in 2 digits only

                NumberFormat f = new DecimalFormat("00");

                long hour = (millisUntilFinished / 3600000) % 24;

                long min = (millisUntilFinished / 60000) % 60;

                long sec = (millisUntilFinished / 1000) % 60;

                binding.timer.setText(f.format(min) + ":" + f.format(sec));

            }

            // When the task is over it will print 00:00:00 there

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Ends .. ", Toast.LENGTH_SHORT).show();
                finish();

            }

        }.start();
        startCoding();
    }

    private void startCoding() {
        binding.Questions.setText(codingQuestions[flag]);
        binding.radioButton1.setText(codingOptions[0]);
        binding.radioButton2.setText(codingOptions[1]);
        binding.radioButton3.setText(codingOptions[2]);
        binding.radioButton4.setText(codingOptions[3]);
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.answersgrp.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    count = count + 1;
                    if (count >= 10) {
                        count = 10;
                    }
                    binding.testName.setText("Coding Test: " + count);
                }

                int radiobuttonId = binding.answersgrp.getCheckedRadioButtonId();
                RadioButton radioButton = binding.getRoot().findViewById(radiobuttonId);
                answerText = radioButton.getText().toString();

                System.out.println("Checked id " + radiobuttonId);
                System.out.println(" answerText " + answerText);

                if (answerText.equals(codingAnswers[flag])) {
                    correct++;
                } else {
                    wrong++;
                }
                flag = flag + 1;

                if (flag < codingQuestions.length) {
                    binding.Questions.setText(codingQuestions[flag]);
                    binding.radioButton1.setText(codingOptions[flag * 4]);
                    binding.radioButton2.setText(codingOptions[flag * 4 + 1]);
                    binding.radioButton3.setText(codingOptions[flag * 4 + 2]);
                    binding.radioButton4.setText(codingOptions[flag * 4 + 3]);
                } else {
                    marks = correct;

                    //total
                    total = correct + Integer.parseInt(aptitude) + Integer.parseInt(technical) + Integer.parseInt(numerical);
                    System.out.println(correct + "Correct");
                    binding.proceed.setVisibility(View.VISIBLE);
                    binding.txtComplete.setVisibility(View.VISIBLE);
                    binding.animationView.setVisibility(View.VISIBLE);
                    binding.next.setVisibility(View.GONE);
                    binding.linearL.setVisibility(View.GONE);
                    binding.proceed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor editor = getSharedPreferences("CodingData", MODE_PRIVATE).edit();
                            editor.putString("coding", String.valueOf(correct));
                            editor.apply();
                            updateResult(correct);
                            startActivity(new Intent(getApplicationContext(), SuccessPage.class).putExtra("coding", correct));
                            finish();
                        }
                    });
                }
                binding.answersgrp.clearCheck();
            }
        });
    }

    private void updateResult(int correct) {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    getPhone();
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
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
                SharedPreferences prefs = getSharedPreferences("SharedData", MODE_PRIVATE);
                final String student_id = prefs.getString("u_id", "No logid");//"No name defined" is the default value.
                final String job_id = prefs.getString("pl_id", "No logid");//"No name defined" is the default value.

                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "updateResult");
                map.put("job_id", job_id);
                map.put("student_id", student_id);
                map.put("aptitude", aptitude);
                map.put("numerical", numerical);
                map.put("technical", technical);
                map.put("coding", String.valueOf(correct));
                map.put("total", String.valueOf(total));
                return map;
            }
        };
        queue.add(request);
    }

    private void getPhone() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    String data = response;
                    String[] respArr = data.trim().split("#");
                    String Phone = respArr[0];
                    System.out.println("Calling :" + Phone);
                    if (total > 15) {

                        String msg = "You have been Passed the Examination held for the Position of " + jobName + " by " + comp_name;
                        System.out.println("msg");

                        SmsManager smsManager = SmsManager.getDefault();
                        ArrayList<String> parts = smsManager.divideMessage(msg);
                        int numParts = parts.size();

                        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>(numParts);
                        ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>(numParts);

                        for (int j = 0; j < numParts; j++) {
                            Intent sentIntent = new Intent("SMS_SENT");
                            Intent deliveryIntent = new Intent("SMS_DELIVERED");
                            sentIntents.add(PendingIntent.getBroadcast(getApplicationContext(), 0, sentIntent, PendingIntent.FLAG_IMMUTABLE));
                            deliveryIntents.add(PendingIntent.getBroadcast(getApplicationContext(), 0, deliveryIntent, PendingIntent.FLAG_IMMUTABLE));
                        }
                        smsManager.sendMultipartTextMessage(Phone, null, parts, sentIntents, deliveryIntents);
                        //test
//                        String msg = "";
//                        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, new Intent("SMS_SENT"), PendingIntent.FLAG_MUTABLE);
//                        SmsManager sms = SmsManager.getDefault();
//                        sms.sendTextMessage(Phone, null, msg, pi, null);
                        System.out.println("SMS SENT GOOD");
                    } else {

                        String msg = "You have been failed the Examination held for the Position of " + jobName + " by " + comp_name;
                        System.out.println("msg");

                        SmsManager smsManager = SmsManager.getDefault();
                        ArrayList<String> parts = smsManager.divideMessage(msg);
                        int numParts = parts.size();

                        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>(numParts);
                        ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>(numParts);

                        for (int j = 0; j < numParts; j++) {
                            Intent sentIntent = new Intent("SMS_SENT");
                            Intent deliveryIntent = new Intent("SMS_DELIVERED");
                            sentIntents.add(PendingIntent.getBroadcast(getApplicationContext(), 0, sentIntent, PendingIntent.FLAG_IMMUTABLE));
                            deliveryIntents.add(PendingIntent.getBroadcast(getApplicationContext(), 0, deliveryIntent, PendingIntent.FLAG_IMMUTABLE));
                        }
                        smsManager.sendMultipartTextMessage(Phone, null, parts, sentIntents, deliveryIntents);


//                        String msg = "You have been failed the Examination held for the Position of ";
//                        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, new Intent("SMS_SENT"), PendingIntent.FLAG_MUTABLE);
//                        SmsManager sms = SmsManager.getDefault();
//                        sms.sendTextMessage(Phone, null, msg, pi, null);
                        System.out.println("SMS SENT WEAK");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
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
                SharedPreferences prefs = getSharedPreferences("SharedData", MODE_PRIVATE);
                final String student_id = prefs.getString("u_id", "No logid");//"No name defined" is the default value.
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "getPhoneNumber");
                map.put("u_id", student_id);
                return map;
            }
        };
        queue.add(request);
    }
}