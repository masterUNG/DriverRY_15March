package com.akexorcist.googledirection.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowResultActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] loginStrings, jobStrings;
    private String lengthString;
    private TextView idDriverTextView, idJobTextView, lengthTextView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        //Initial View
        initialView();

        //Get Value From Intent
        getValueFromIntent();

        //Show View
        showView();

        //Edit Status
        editStatus();

        //Button Controller
        buttonController();


    }   // Main Method

    private void buttonController() {
        button.setOnClickListener(ShowResultActivity.this);
    }

    private void editStatus() {

        try {

            //For Edit Status at jobTABLE
            EditStatusTo2 editStatusTo2 = new EditStatusTo2(ShowResultActivity.this,
                    loginStrings[0], "4", "5");
            editStatusTo2.execute();
            if (Boolean.parseBoolean(editStatusTo2.get())) {
                Log.d("21MarchV3", "Status 4 ==> 5 OK for jobTABLE");
            }

            //For Edit status at userTABLE_ry
            EditStatusDriver editStatusDriver = new EditStatusDriver(ShowResultActivity.this,
                    loginStrings[0], "5");
            editStatusDriver.execute();
            if (Boolean.parseBoolean(editStatusDriver.get())) {
                Log.d("21MarchV3", "Status 4 ==> 5 OK for userTABLE");
            }


            //For Edit Length to Column Length
            EditLengthWhereId editLengthWhereId = new EditLengthWhereId(ShowResultActivity.this,
                    loginStrings[0], lengthString);
            editLengthWhereId.execute();



        } catch (Exception e) {
            Log.d("21MarchV3", "e editStatus ==> " + e.toString());
        }

    }   // editStatus

    private void initialView() {
        idDriverTextView = (TextView) findViewById(R.id.txtIDDriver);
        idJobTextView = (TextView) findViewById(R.id.txtIDjob);
        lengthTextView = (TextView) findViewById(R.id.txtLength);
        button = (Button) findViewById(R.id.btnConfirm);

    }

    private void showView() {
        idDriverTextView.setText("idDriver ==> " + loginStrings[0]);
        idJobTextView.setText("idJob ==> " + jobStrings[0]);
        lengthTextView.setText("Length ==> " + lengthString);
    }

    private void getValueFromIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login");
        jobStrings = getIntent().getStringArrayExtra("ID_job");
        lengthString = getIntent().getStringExtra("Length");

        Log.d("21MarchV2", "jobString.length ==> " + jobStrings.length);
        for (int i = 0; i < jobStrings.length; i++) {
            Log.d("21MarchV2", "jobString(" + i + ") ==> " + jobStrings[i]);
        }

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ShowResultActivity.this, WalkActivity.class);
        intent.putExtra("Login", loginStrings);
        startActivity(intent);
        finish();
    }
}   // Main Class
