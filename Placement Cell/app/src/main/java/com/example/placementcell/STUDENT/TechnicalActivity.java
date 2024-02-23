package com.example.placementcell.STUDENT;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementcell.databinding.ActivityTechnicalBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TechnicalActivity extends AppCompatActivity {
    public static int marks = 0, correct = 0, wrong = 0;
    ActivityTechnicalBinding binding;
    String answerText;
    int flag = 0, count = 1;
    String[] technicalQuestions = {
            "Which of the following does not represent a valid storage class in c?",
            "A pointer in c which has not been initialized is known as?",
            "In C++, dynamic memory allocation is achieved with the operator?",
            "The smallest integer that can be represented by an 8-bit number in 2’s complement form is?",
            "What does JVM stands for?",
            "What is Portability offered by Java language?",
            "Choose the correct identifier for a method name in Java",
            "A Java Class inherits Constants and Methods of an Interface using ____ keyword.",
            "All Interface variables are ___ by default in Java ?",
            "A Static method of an Interface should be accessed with _____ and a DOT operator?",
    };

    String[] technicalOptions = {
            "static", "union", "extern", "automatic",
            "far pointer", "void pointer", "null pointer", "wild pointer",
            "new", "this", "malloc( )", "calloc()",
            "-256", "-128", "-127", "0",
            "Java Variable Machine", "Java Virtual Machine", "Java Virtual Mechanism", "None of the above",
            "Small code size easy to carry occupying less disk space", "Generating suitable Byte Code for each machine by the Compiler", "Ability to run the Byte on different machines producing the same behaviour and output", "Java does not actually provide portability",
            "1show", "$hide", "*show$", "3_click",
            "INTERFACE", "IMPLEMENTS", "EXTENDS", "All the above",
            "public", "final", "public and final", "None",
            "Class Name", "Interface Name", "An object of a concrete class", "None of the above",
    };

    String[] technicalAnswers = {
            "union",
            "wild pointer",
            "new",
            "-128",
            "Java Virtual Machine",
            "Ability to run the Byte on different machines producing the same behaviour and output",
            "$hide",
            "IMPLEMENTS",
            "public and final",
            "Interface Name",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTechnicalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.testName.setText("Technical Round: " + count);

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
        startTechnical();
    }

    private void startTechnical() {
        binding.Questions.setText(technicalQuestions[flag]);
        binding.radioButton1.setText(technicalOptions[0]);
        binding.radioButton2.setText(technicalOptions[1]);
        binding.radioButton3.setText(technicalOptions[2]);
        binding.radioButton4.setText(technicalOptions[3]);
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
                    binding.testName.setText("Technical Round: " + count);
                }

                int radiobuttonId = binding.answersgrp.getCheckedRadioButtonId();
                RadioButton radioButton = binding.getRoot().findViewById(radiobuttonId);
                answerText = radioButton.getText().toString();

                System.out.println("Checked id " + radiobuttonId);
                System.out.println(" answerText " + answerText);

                if (answerText.equals(technicalAnswers[flag])) {
                    correct++;
                } else {
                    wrong++;
                }
                flag = flag + 1;


                if (binding.score != null)
                    // binding.score.setText("" + correct);

                    if (flag < technicalQuestions.length) {
                        binding.Questions.setText(technicalQuestions[flag]);
                        binding.radioButton1.setText(technicalOptions[flag * 4]);
                        binding.radioButton2.setText(technicalOptions[flag * 4 + 1]);
                        binding.radioButton3.setText(technicalOptions[flag * 4 + 2]);
                        binding.radioButton4.setText(technicalOptions[flag * 4 + 3]);
                    } else {
                        marks = correct;

                        System.out.println(correct + "Correct");
                        binding.proceed.setVisibility(View.VISIBLE);
                        binding.next.setVisibility(View.GONE);
                        binding.linearL.setVisibility(View.GONE);
                        binding.proceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPreferences.Editor editor = getSharedPreferences("TechnicalData", MODE_PRIVATE).edit();
                                editor.putString("technical", String.valueOf(correct));
                                editor.apply();

                                startActivity(new Intent(getApplicationContext(), CodingTest.class).putExtra("technical", correct));
                                finish();
                            }
                        });
                    }
                binding.answersgrp.clearCheck();
            }
        });
    }
}