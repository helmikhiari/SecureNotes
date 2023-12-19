package com.example.notice.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.notice.R;

public class Auth extends AppCompatActivity {

    int hour,minutes;
    Button check;
    TimePicker timePicker;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
         timePicker = findViewById(R.id.timePicker1);
        check=findViewById(R.id.setalarm);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettime();
                if ((hour==3)&&(minutes==15))
                {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Alarm Set with Success", Toast.LENGTH_SHORT).show();
                }
                }


        });
    }
    public void gettime() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
        } else {
            hour = timePicker.getCurrentHour();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            minutes = timePicker.getMinute();
        } else {
            minutes = timePicker.getCurrentMinute();
        }
    }
}