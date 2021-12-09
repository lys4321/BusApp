package com.example.busstopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.busstopapp.API.GpsTracker;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        GpsTracker gpsTracker = new GpsTracker(MainActivity2.this);
        double currentLatitude = gpsTracker.getLatitude();
        double currentLongitude = gpsTracker.getLongitude();
        Log.i("가자", currentLatitude +","+currentLongitude);
    }
}