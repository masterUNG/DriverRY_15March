package com.akexorcist.googledirection.sample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConfirmJob extends AppCompatActivity {

    //Explicit
    private String[] loginString;
    private String[] tagStrings = new String[]{"1decV1"};
    private Boolean aBoolean = true, restartABoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_job);

        //Get Value of Login Pass จะรู้ว่าใคร Login
        getValueFromIntent();

        //Login Status ==> 1
        // editStatus(1);

        //Check Status on userTABLE
        checkStatus();

        //Check Job
        checkJob();

    }   // Main Method

    private void getValueFromIntent() {
        loginString = getIntent().getStringArrayExtra("Login");
        for (int i = 0; i < loginString.length; i++) {
            Log.d("29decV1", "loginString(" + i + ")==>" + loginString[i]);
        }   // for
    }

    private void checkStatus() {

        try {

            FindStatusDriver findStatusDriver = new FindStatusDriver(ConfirmJob.this);
            findStatusDriver.execute(loginString[0]);
            String strJSON = findStatusDriver.get();
            Log.d("15febV1", "strJSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String strStatus = jsonObject.getString("Status");
            Log.d("15febV1", "Status ==> " + strStatus);

            int intStatus = Integer.parseInt(strStatus);
            if (intStatus == 2) {
                goToService1();
            } else if (intStatus == 3) {
                goToService2();
            } else if (intStatus == 4) {
                goToMonitor();
            } else if (intStatus == 5) {
                goToShowResult();
            } else if (intStatus == 6) {
                goToWalk();
            } else if (intStatus == 7) {
                goToBackOffice();
            }


        } catch (Exception e) {
            Log.d("15febV1", "e checkStatus ==> " + e.toString());
        }

    }   // checkStatus

    private void goToBackOffice() {
        Intent intent = new Intent(ConfirmJob.this, BackOfficeActivity.class);
        intent.putExtra("Login", loginString);
        startActivity(intent);
        finish();
    }

    private void goToService2() {
        Intent intent = new Intent(ConfirmJob.this, ServiceActivity.class);
        intent.putExtra("Login", loginString);
        intent.putExtra("aBoolean2", true);
        startActivity(intent);
        finish();
    }

    private void goToService1() {
        Intent intent = new Intent(ConfirmJob.this, ServiceActivity.class);
        intent.putExtra("Login", loginString);
        startActivity(intent);
        finish();
    }

    private void goToWalk() {
        Intent intent = new Intent(ConfirmJob.this, WalkActivity.class);
        intent.putExtra("Login", loginString);
        startActivity(intent);
        finish();
    }

    private void goToShowResult() {

        try {

            GetJobWhereIdDriverStatus getJobWhereIdDriverStatus = new GetJobWhereIdDriverStatus(ConfirmJob.this);
            getJobWhereIdDriverStatus.execute(loginString[0], "5");
            String s = getJobWhereIdDriverStatus.get();

            Log.d("22MarchV1", "JSON ==> " + s);

            MyConstant myConstant = new MyConstant();
            String[] columnStrings = myConstant.getJobStrings();
            String[] jobStrings = new String[columnStrings.length];

            JSONArray jsonArray = new JSONArray(s);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            for (int i=0;i<jobStrings.length;i++) {
                jobStrings[i] = jsonObject.getString(columnStrings[i]);
                Log.d("22MarchV1", "jobStrings(" + i + ") ==> " + jobStrings[i]);
            }

            Intent intent = new Intent(ConfirmJob.this, ShowResultActivity.class);
            intent.putExtra("Login", loginString);
            intent.putExtra("ID_job", jobStrings);
            intent.putExtra("Length", jobStrings[12]);
            startActivity(intent);
            finish();


        } catch (Exception e) {
            Log.d("22MarchV1", "e goToShowResult ==> " + e.toString());
        }

    }   // goToShowResult

    private void goToMonitor() {

        try {

            GetJobWhereIdDriverStatus getJobWhereIdDriverStatus = new GetJobWhereIdDriverStatus(ConfirmJob.this);
            getJobWhereIdDriverStatus.execute(loginString[0], "4");
            String strJSON = getJobWhereIdDriverStatus.get();

            Log.d("17MarchV2", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            double douLat = Double.parseDouble(jsonObject.getString("Lat_end"));
            double douLng = Double.parseDouble(jsonObject.getString("Lng_end"));

            Log.d("17MarchV2", "Lat ==> " + douLat);
            Log.d("17MarchV2", "Lng ==> " + douLng);

            Intent intent = new Intent(ConfirmJob.this, MonitorActivity.class);
            intent.putExtra("Login", loginString);
            intent.putExtra("Lat", douLat);
            intent.putExtra("Lng", douLng);
            startActivity(intent);


        } catch (Exception e) {
            Log.d("17MarchV2", "e goTo ==> " + e.toString());
        }

    }   // goToMonitor

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("2decV2", "onRestart Work");

        if (restartABoolean) {
            Log.d("2decV2", "onRestart Work in Condition");

            //Edit Status Column to 2
            aBoolean = true;
            restartABoolean = false;

            try {

                //Update Status of jobTABLE
                EditStatusTo2 editStatusTo2 = new EditStatusTo2(ConfirmJob.this,
                        loginString[0], "0", "1");   // เกิดจากการที่ คนขับรถ ปฎิเสธงาน
                editStatusTo2.execute();

                Log.d("29decV2", "Result jobTABLE ==> " + editStatusTo2.get());

                //Update Status of userTABLE_ry
                EditStatusDriver editStatusDriver = new EditStatusDriver(ConfirmJob.this,
                        loginString[0], "1");
                editStatusDriver.execute();
                Log.d("29decV2", "Result userTABLE ==> " + editStatusDriver.get());


            } catch (Exception e) {
                Log.d("2decV2", "e onRestate ==> " + e.toString());
            }


        }

    }   // onRestart

    private void checkJob() {

        //TodoIt
        try {

            Log.d("2decV1", "idDriver ที่ส่งไป ==> " + loginString[0]);

            // ทำการค้นหางานที่ มี id ของ Driver ตรงกับคนทีี่ Login และ Status ที่ 0 บน jobTABLE
            MyCheckJob myCheckJob = new MyCheckJob(ConfirmJob.this, loginString[0], "0");
            myCheckJob.execute();
            String s = myCheckJob.get();
            Log.d("2decV1", "JSON ที่อ่านได้ ==> " + s);

            Log.d("2decV1", "Condition ที่เห็น ==> " + (!s.equals("null")));

            if (!s.equals("null")) {

                if (aBoolean) {
                    aBoolean = false;
                    myNotification();
                }

            } // if

        } catch (Exception e) {
            Log.d("1decV2", "e checkJob ==> " + e.toString());
        }

        // ทุกๆ 1 วินาทีจะทำไปเรื่อยๆ
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkJob();

            }
        }, 1000);

    } // CheckJob

    private void myNotification() {

        Log.d("2decV1", "Notification Work");

        restartABoolean = true;

        Intent intent = new Intent(ConfirmJob.this, NotificationAlert.class);
        intent.putExtra("Login", loginString);

        PendingIntent pendingIntent = PendingIntent.getActivity(ConfirmJob.this,
                (int) System.currentTimeMillis(), intent, 0);

        // กำหนด การใช้เสียงเอง
        Uri soundUri = Uri.parse("android.resource://" +
                ConfirmJob.this.getPackageName() +
                "/" +
                R.raw.bells);


        Notification.Builder builder = new Notification.Builder(ConfirmJob.this);
        builder.setTicker("Driver ry");
        builder.setContentTitle("งานมาใหม่ค่ะ");
        builder.setContentText("กรุณาคลิ๊กที่นี้");
        builder.setSmallIcon(R.drawable.doremon48);
        builder.setSound(soundUri, RingtoneManager.TYPE_ALARM);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        notification.flags |= Notification.DEFAULT_LIGHTS
                | Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_ONLY_ALERT_ONCE;

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, notification);


    }   //myNotification

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("1decV1", "onStop");

        finish();
        // editStatus(0);

    }

    private void editStatus(int intStatus) {

        try {

            String s = null;
            EditStatusDriver editStatusDriver = new EditStatusDriver(ConfirmJob.this,
                    loginString[0],
                    Integer.toString(intStatus));
            editStatusDriver.execute();

            if (Boolean.parseBoolean(editStatusDriver.get())) {
                s = "Change Status OK";
            } else {
                s = "Cannot Change Status";
            }

            Toast.makeText(ConfirmJob.this, s, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.d(tagStrings[0], "e editstatus ==> " + e.toString());

        }

    }
}   //Main Class
