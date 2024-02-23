package com.example.placementcell.STUDENT;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementcell.databinding.ActivityTestBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TestActivity extends AppCompatActivity {
    public static int marks = 0, correct = 0, wrong = 0;
    ActivityTestBinding binding;
    String answerText;

    String[] aptitudeQuestions = {
            "56% of Y is 182. What is Y? ",
            "Which of the following two ratios is greater 17:18 and 10:11? ",
            "A train is 31m long. It passes a platform which is 175 m long. What is the distance covered by the train in passing the platform? ",
            "How much will be the average of the squares of natural numbers from 1 to 35? ",
            "A train moves with the speed of 180 km/hr. Its speed (in metres per second) is",
            "What is probability of drawing two clubs from a well shuffled pack of 52 cards?",
            "What are the chances that no two boys are sitting together for a photograph if there are 5 girls and 2 boys?",
            "Which of the following is in descending order?",
            "What is value of 945.341-1042.792+875.435+31.025?",
            "A man goes to Mumbai from Pune at a speed of 4 km/hr and returns to Pune at speed of 6km/hr. What is his average speed of the entire journey?",
    };

    String[] aptitudeOptions = {
            "350", "364", "325", "360",
            "17/18", "10/11", "Both are same", "Cannot determine",
            "206 m", "144 m", "175/31 m", "Cannot be determined",
            "612.5", "1225", "426", "324",
            "5", "40", "30", "50",
            "13/51", "1/17", "1/26", "13/17",
            "1/21", "4/7", "2/7", "5/7",
            "5/8; 9/13; 11/17", "5/8; 11/17; 9/13", "9/13; 11/17; 5/8", "11/17; 9/13; 5/8",
            "908.004", "810.008", "795.659", "809.009",
            "4.8km/hr", "5 km/hr", "4.2 km/hr", "5.6 km/hr",
    };

    String[] aptitudeAnswers = {
            "325",
            "17/18",
            "206 m",
            "426",
            "50",
            "1/17",
            "5/7",
            "9/13; 11/17; 5/8",
            "809.009",
            "4.8km/hr",
    };
    int flag = 0, count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.testName.setText("Aptitude Round: " + count);

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
        startAptitude();
    }

    private void startAptitude() {
        binding.Questions.setText(aptitudeQuestions[flag]);
        binding.radioButton1.setText(aptitudeOptions[0]);
        binding.radioButton2.setText(aptitudeOptions[1]);
        binding.radioButton3.setText(aptitudeOptions[2]);
        binding.radioButton4.setText(aptitudeOptions[3]);
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
                    binding.testName.setText("Aptitude Round: " + count);
                }

                int radiobuttonId = binding.answersgrp.getCheckedRadioButtonId();
                RadioButton radioButton = binding.getRoot().findViewById(radiobuttonId);
                answerText = radioButton.getText().toString();

                System.out.println("Checked id " + radiobuttonId);
                System.out.println(" answerText " + answerText);

                if (answerText.equals(aptitudeAnswers[flag])) {
                    correct++;
                } else {
                    wrong++;
                }
                flag = flag + 1;


                if (binding.score != null)
                    // binding.score.setText("" + correct);

                    if (flag < aptitudeQuestions.length) {
                        binding.Questions.setText(aptitudeQuestions[flag]);
                        binding.radioButton1.setText(aptitudeOptions[flag * 4]);
                        binding.radioButton2.setText(aptitudeOptions[flag * 4 + 1]);
                        binding.radioButton3.setText(aptitudeOptions[flag * 4 + 2]);
                        binding.radioButton4.setText(aptitudeOptions[flag * 4 + 3]);
                    } else {
                        marks = correct;

                        binding.proceed.setVisibility(View.VISIBLE);
                        binding.next.setVisibility(View.GONE);
                        binding.linearL.setVisibility(View.GONE);
                        binding.proceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPreferences.Editor editor = getSharedPreferences("AptitudeData", MODE_PRIVATE).edit();
                                editor.putString("aptitude", String.valueOf(correct));
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(), NumericalActivity.class).putExtra("aptitude", correct));
                                finish();
                            }
                        });
                        System.out.println(correct + "Correct");
                    }
                binding.answersgrp.clearCheck();
            }
        });
    }
}

