package com.example.placementcell.STUDENT;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementcell.databinding.ActivitySuccessPageBinding;

public class SuccessPage extends AppCompatActivity {
    ActivitySuccessPageBinding binding;
    SharedPreferences preferences;
    String aptitude, numerical, technical, coding;
    SharedPreferences codingPref, aptitudePref, technicalPref, numericalPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuccessPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        aptitudePref = getSharedPreferences("AptitudeData", MODE_PRIVATE);
        numericalPref = getSharedPreferences("NumericalData", MODE_PRIVATE);
        codingPref = getSharedPreferences("CodingData", MODE_PRIVATE);
        technicalPref = getSharedPreferences("TechnicalData", MODE_PRIVATE);

        aptitude = aptitudePref.getString("aptitude", "");
        numerical = numericalPref.getString("numerical", "");
        technical = technicalPref.getString("technical", "");
        coding = codingPref.getString("coding", "");

        System.out.println("Aptitude:" + aptitude + "Numerical:" + numerical + "Technical:" + technical + "Coding:" + coding);

        binding.aptitude.setText(aptitude + "/10");
        binding.numerical.setText(numerical + "/10");
        binding.technical.setText(technical + "/10");
        binding.coding.setText(coding + "/10");
        int f = Integer.parseInt(aptitude);
        int f2 = Integer.parseInt(numerical);
        int f3 = Integer.parseInt(technical);
        int f4 = Integer.parseInt(coding);
        int[] FINAL = {f, f2, f3, f4};
        getMARK(FINAL);


        //weak student study materials

        binding.cprogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.sanfoundry.com/c-interview-questions-answers/";
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
            }
        });

        binding.technicalMcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.tutorialspoint.com/questions_and_answers.htm";
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
            }
        });

        binding.numericalMcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.javatpoint.com/reasoning-mcq";
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
            }
        });

        binding.aptitudeMcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://mettl.com/test/aptitude-test-for-software-developer/";
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
            }
        });


        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences aptitudePref = getSharedPreferences("AptitudeData", Context.MODE_PRIVATE);
//                SharedPreferences numericPref = getSharedPreferences("NumericalData", Context.MODE_PRIVATE);
//                SharedPreferences codingPref = getSharedPreferences("CodingData", Context.MODE_PRIVATE);
//                SharedPreferences technicalPref = getSharedPreferences("TechnicalData", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.clear();
//                editor.apply();
                finish();
                startActivity(new Intent(getApplicationContext(), Student.class));
            }
        });
    }

    void getMARK(int arr[]) {
        int i, m1, m2, a, b, n = 0, Ans = 0;
        boolean flag;
        float sum1, sum2, total;
        a = arr[0];
        b = arr[1];
        m1 = a;
        m2 = b;
        int cluster1[] = new int[arr.length], cluster2[] = new int[arr.length];
        do {
            sum1 = 0;
            sum2 = 0;
            cluster1 = new int[arr.length];
            cluster2 = new int[arr.length];
            n++;
            int k = 0, j = 0;
            for (i = 0; i < arr.length; i++) {
                if (Math.abs(arr[i] - m1) <= Math.abs(arr[i] - m2)) {
                    cluster1[k] = arr[i];
                    k++;
                } else {
                    cluster2[j] = arr[i];
                    j++;
                }
            }
            System.out.println();
            for (i = 0; i < k; i++) {
                sum1 = sum1 + cluster1[i];
            }
            for (i = 0; i < j; i++) {
                sum2 = sum2 + cluster2[i];
            }
            //printing Centroids/Means\
            System.out.println("m1=" + m1 + "   m2=" + m2);
            a = m1;
            b = m2;
            m1 = Math.round(sum1 / k);
            m2 = Math.round(sum2 / j);
            flag = !(m1 == a && m2 == b);

            System.out.println("After iteration " + n + " , cluster 1 :\n");    //printing the clusters of each iteration
            for (i = 0; i < cluster1.length; i++) {
                System.out.print(cluster1[i] + "\t");
            }

            System.out.println("\n");
            System.out.println("After iteration " + n + " , cluster 2 :\n");
            for (i = 0; i < cluster2.length; i++) {
                System.out.print(cluster2[i] + "\t");
            }

        } while (flag);

        System.out.println("Final cluster 1 :\n");            // final clusters
        for (i = 0; i < cluster1.length; i++) {
            System.out.print(cluster1[i] + "\t");
        }

        System.out.println();
        System.out.println("Final cluster 2 :\n");
        for (i = 0; i < cluster2.length; i++) {
            Ans += cluster2[i];
            System.out.print(cluster2[i] + "\t");
        }
        System.out.println("");


        System.out.println("Sum 1 of Cluster Answer :" + sum1);
        System.out.println("sum2  of Cluster Answer :" + sum2);
        System.out.println("Final Cluster Answer :" + sum1 + sum2);
        total = sum1 + sum2;
        binding.total.setText(String.valueOf(sum1 + sum2));

        if (total < 30) {
            binding.cprogram.setVisibility(View.VISIBLE);
            binding.aptitudeMcq.setVisibility(View.VISIBLE);
            binding.numericalMcq.setVisibility(View.VISIBLE);
            binding.technicalMcq.setVisibility(View.VISIBLE);
        }
    }
}