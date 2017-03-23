package com.akexorcist.googledirection.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class WalkActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private Button button;
    private String[] loginStrings;
    private int startMinusAnInt, walkMinusAnInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        //Initial View
        button = (Button) findViewById(R.id.btnOK);

        //Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        //Button Controller
        button.setOnClickListener(WalkActivity.this);

        //Update Status
        updateStatus("5", "6");

        //Get WalkTime
        getWalkTime();

    }   // Main Method

    private void getWalkTime() {
        Calendar calendar = Calendar.getInstance();
        startMinusAnInt = (calendar.get(Calendar.HOUR_OF_DAY) * 60) + calendar.get(Calendar.MINUTE);
        Log.d("22MarchV3", "startMinus ==> " + startMinusAnInt);

    }

    private void updateStatus(String oldStatus, String newStatus) {

        try {

            Log.d("22MarchV2", "idDriver ==> " + loginStrings[0]);
            Log.d("22MarchV2", "oldStatus ==> " + oldStatus);
            Log.d("22MarchV2", "newStatus ==> " + newStatus);

            //For update Status on jobTABLE
            EditStatusTo2 editStatusTo2 = new EditStatusTo2(WalkActivity.this,
                    loginStrings[0], oldStatus, newStatus);
            editStatusTo2.execute();

            if (Boolean.parseBoolean(editStatusTo2.get())) {
                Log.d("22MarchV2", "Edit Status to mySQL OK");
            }

            //For update Status userTABLE
            EditStatusDriver editStatusDriver = new EditStatusDriver(WalkActivity.this,
                    loginStrings[0], newStatus);
            editStatusDriver.execute();

            if (Boolean.parseBoolean(editStatusDriver.get())) {
                Log.d("22MarchV2", "Update 6 to userTABLE OK");
            }

        } catch (Exception e) {
            Log.d("22MarchV2", "e updateStatus ==> " + e.toString());
        }

    }   // updateStatus

    @Override
    public void onClick(View view) {

        if (view == button) {

            //Find WalkTime
            Calendar calendar = Calendar.getInstance();
            walkMinusAnInt = ((calendar.get(Calendar.HOUR_OF_DAY) * 60) + calendar.get(Calendar.MINUTE)) - startMinusAnInt;
            Log.d("22MarchV3", "walkMinus ==> " + walkMinusAnInt);

            //Edit Status and WalkTime to Server
            editStatusWalkTime();


        }   // if

    }   // onClick

    private void editStatusWalkTime() {
        try {

            //For Update WalkTime
            EditWalkTimeWhereIdStatus editWalkTimeWhereIdStatus = new EditWalkTimeWhereIdStatus(WalkActivity.this);
            editWalkTimeWhereIdStatus.execute(loginStrings[0], Integer.toString(walkMinusAnInt));

            if (Boolean.parseBoolean(editWalkTimeWhereIdStatus.get())) {
                Log.d("22MarchV3", "Update WalkTime OK");
            }

            //For Update Status @ userTABLE
            EditStatusDriver editStatusDriver = new EditStatusDriver(WalkActivity.this,
                    loginStrings[0], "7");
            editStatusDriver.execute();

            if (Boolean.parseBoolean(editStatusDriver.get())) {
                Log.d("22MarchV3", "Update Status @ userTABLE OK");
            }

            Intent intent = new Intent(WalkActivity.this, BackOfficeActivity.class);
            intent.putExtra("Login", loginStrings);
            startActivity(intent);
            finish();


        } catch (Exception e) {
            Log.d("22MarchV3", "e onClick ==> " + e.toString());
        }
    }
}   // Main Class
