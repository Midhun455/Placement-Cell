package com.example.placementcell;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementcell.OutSideStudents.OutSideHome;
import com.example.placementcell.PLACEMENT_OFFICER.Officer;
import com.example.placementcell.STUDENT.Student;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        preferences = getSharedPreferences("SharedData", MODE_PRIVATE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String type = preferences.getString("type", "No logid");
                System.out.println("&&&&&&&&&&&&&&&" + type);
                switch (type) {
                    case "OFFICER":
                        startActivity(new Intent(getApplicationContext(), Officer.class));
                        finish();
                        break;
                    case "STUDEXTERNAL":
                        startActivity(new Intent(getApplicationContext(), OutSideHome.class));
                        finish();
                        break;
                    case "STUDCOMMON":
                        startActivity(new Intent(getApplicationContext(), Student.class));
                        finish();
                        break;
                    default:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                }
            }
        }, 3000);
    }
}
