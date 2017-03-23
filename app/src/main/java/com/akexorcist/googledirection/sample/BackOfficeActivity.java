package com.akexorcist.googledirection.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

public class BackOfficeActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private Button button;
    private String[] loginStrings;
    private String idJobString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_office);

        //Initial View
        button = (Button) findViewById(R.id.btnBackOffice);

        //Get Value From Intent and Server
        getValueFromIntentAnServer();

        //Button Controller
        button.setOnClickListener(BackOfficeActivity.this);


    }   // Main Method

    private void getValueFromIntentAnServer() {

        //For Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        //For idJob
        try {

            GetJobWhereIdDriverStatus getJobWhereIdDriverStatus = new GetJobWhereIdDriverStatus(BackOfficeActivity.this);
            getJobWhereIdDriverStatus.execute(loginStrings[0], "6");
            String strJSON = getJobWhereIdDriverStatus.get();

            Log.d("23MarchV1", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            int lastIndex = jsonArray.length() - 1;

            JSONObject jsonObject = jsonArray.getJSONObject(lastIndex);
            idJobString = jsonObject.getString("id");
            Log.d("23MarchV1", "idJobString ==> " + idJobString);

        } catch (Exception e) {
            Log.d("23MarchV1", "e getValueFromIntent ==> " + e.toString());
        }



    }

    @Override
    public void onClick(View view) {

        if (view == button) {
            Intent intent = new Intent(BackOfficeActivity.this, PhotoActivity.class);

            intent.putExtra("id_Driver", loginStrings[0]);
            intent.putExtra("id_job", idJobString);
            intent.putExtra("Mode", 1);
            intent.putExtra("Login", loginStrings);

            startActivity(intent);
            finish();
        }

    }
}   // Main Class
