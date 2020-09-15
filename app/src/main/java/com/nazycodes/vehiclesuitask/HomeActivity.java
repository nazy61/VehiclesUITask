package com.nazycodes.vehiclesuitask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class HomeActivity extends AppCompatActivity {

    private CardView cvCar, cvHelicopter, cvBoat, cvScooter, cvTruck, cvPlane;
    private ProgressBar progressBar;

    private boolean cvCarSelected = false;
    private boolean cvHelicopterSelected = false;
    private boolean cvBoatSelected = false;
    private boolean cvScooterSelected = false;
    private boolean cvTruckSelected = false;
    private boolean cvPlaneSelected = false;

    private int noOfSelectedItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acitivity);

        progressBar = findViewById(R.id.progress);
        cvCar = findViewById(R.id.cvCar);
        cvHelicopter = findViewById(R.id.cvHelicopter);
        cvBoat = findViewById(R.id.cvBoats);
        cvScooter = findViewById(R.id.cvScooters);
        cvTruck = findViewById(R.id.cvTrucks);
        cvPlane = findViewById(R.id.cvPlanes);

        cvCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCardColor(cvCar, cvCarSelected);
                cvCarSelected = !cvCarSelected;
            }
        });

        cvHelicopter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCardColor(cvHelicopter, cvHelicopterSelected);
                cvHelicopterSelected = !cvHelicopterSelected;
            }
        });

        cvBoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCardColor(cvBoat, cvBoatSelected);
                cvBoatSelected = !cvBoatSelected;
            }
        });

        cvScooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCardColor(cvScooter, cvScooterSelected);
                cvScooterSelected = !cvScooterSelected;
            }
        });

        cvTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCardColor(cvTruck, cvTruckSelected);
                cvTruckSelected = !cvTruckSelected;
            }
        });

        cvPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCardColor(cvPlane, cvPlaneSelected);
                cvPlaneSelected = !cvPlaneSelected;
            }
        });
    }

    private void toggleCardColor(CardView cardView, boolean selected) {
        if(selected){
            cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            noOfSelectedItems -= 1;
        } else {
            cardView.setCardBackgroundColor(Color.parseColor("#9000FF"));
            noOfSelectedItems += 1;
        }

        updateProgressBar();
    }

    private void updateProgressBar() {
        int progress = (noOfSelectedItems * 100) / 6;
        if(progress <= 0){
            progressBar.setProgress(1);
        } else {
            progressBar.setProgress(progress);
        }
    }
}