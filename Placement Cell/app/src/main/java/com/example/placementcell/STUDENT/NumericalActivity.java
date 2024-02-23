package com.example.placementcell.STUDENT;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementcell.databinding.ActivityNumericalBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumericalActivity extends AppCompatActivity {
    public static int marks = 0, correct = 0, wrong = 0;
    ActivityNumericalBinding binding;
    String answerText;
    int flag = 0, count = 1;
    String[] numericalQuestions = {
            "How much significant digits in this number 204.020050?",
            "What will be the value of x3 + y3 + z3, if x + y + z = 0?",
            "Digit 1 is occurring 136 times on writing all of the page numbers of a book. What will be the number of pages in the book?",
            "Which of the following is the unit digit in the product of 853 x 452 x 226 x 1346?",
            "The sum of odd numbers upTo 240 is - ?",
            "Which of the following number is divisible by 9?",
            "What smallest number should be subtracted from 9805 so that it is divisible by 8?",
            "Which of the following is completely divisible by 45?",
            "If the two-third of three - fourth of a number is 34, what will be the 20% of that number?",
            "7X2 is a three-digit number in which X is a missing digit. If the number is divisible by 6, the missing digit is - ?",
    };

    String[] numericalOptions = {
            "5", "7", "9", "11",
            "3xyz", "2xyz", "xyz", "xyz(xy + yz + zx)",
            "194", "195", "200", "295",
            "2", "5", "6", "7",
            "11400", "12400", "13400", "14400",
            "56785", "45678", "65889", "67578",
            "6", "7", "5", "8",
            "331145", "306990", "181660", "None of the above",
            "13.4", "13.6", "13.7", "14",
            "4", "3", "7", "5",
    };

    String[] numericalAnswers = {
            "9",
            "3xyz",
            "195",
            "6",
            "14400",
            "65889",
            "5",
            "306990",
            "13.6",
            "3",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNumericalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Apt", MODE_PRIVATE);
//        String data = preferences.getString("numerical", "");
//        System.out.println(data + "numerical");

        binding.testName.setText("Numerical Round: " + count);

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
        startNumerical();
    }

    private void startNumerical() {
        binding.Questions.setText(numericalQuestions[flag]);
        binding.radioButton1.setText(numericalOptions[0]);
        binding.radioButton2.setText(numericalOptions[1]);
        binding.radioButton3.setText(numericalOptions[2]);
        binding.radioButton4.setText(numericalOptions[3]);
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
                    binding.testName.setText("Numerical Round: " + count);
                }

                int radiobuttonId = binding.answersgrp.getCheckedRadioButtonId();
                RadioButton radioButton = binding.getRoot().findViewById(radiobuttonId);
                answerText = radioButton.getText().toString();

                System.out.println("Checked id " + radiobuttonId);
                System.out.println("answerText " + answerText);

                if (answerText.equals(numericalAnswers[flag])) {
                    correct++;
                } else {
                    wrong++;
                }
                flag = flag + 1;


                if (binding.score != null)
                    // binding.score.setText("" + correct);

                    if (flag < numericalQuestions.length) {
                        binding.Questions.setText(numericalQuestions[flag]);
                        binding.radioButton1.setText(numericalOptions[flag * 4]);
                        binding.radioButton2.setText(numericalOptions[flag * 4 + 1]);
                        binding.radioButton3.setText(numericalOptions[flag * 4 + 2]);
                        binding.radioButton4.setText(numericalOptions[flag * 4 + 3]);
                    } else {
                        marks = correct;

                        System.out.println(correct + "Correct");
                        binding.proceed.setVisibility(View.VISIBLE);
                        binding.next.setVisibility(View.GONE);
                        binding.linearL.setVisibility(View.GONE);
                        binding.proceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPreferences.Editor editor = getSharedPreferences("NumericalData", MODE_PRIVATE).edit();
                                editor.putString("numerical", String.valueOf( correct));
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(), TechnicalActivity.class).putExtra("numerical", correct));
                                finish();
                            }
                        });
                    }
                binding.answersgrp.clearCheck();
            }
        });
    }
}