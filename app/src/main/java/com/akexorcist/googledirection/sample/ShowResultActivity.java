package com.akexorcist.googledirection.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class ShowResultActivity extends AppCompatActivity {

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


    }   // Main Method

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

}   // Main Class
