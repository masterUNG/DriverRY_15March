package com.akexorcist.googledirection.sample;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by DARK on 1/12/2559.
 */

public class EditStatusDriver extends AsyncTask<Void, Void, String> {
    private Context context;
    private MyConstant myConstant = new MyConstant();
    private String urlPHP = myConstant.getUrlEditStatusDriver();
    private String idString, statusString;
    private static final String tag = "1decv1";

    public EditStatusDriver(Context context,
                            String idString,
                            String statusString) {
        this.context = context;
        this.idString = idString;   // id ของ Driver ที่ต้องการเปลี่ยน Status
        this.statusString = statusString; // Status ที่ต้องการจะเปลียน ไปเป็น
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("id", idString)
                    .add("Status", statusString)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            Log.d(tag, "e doIn ==> " + e.toString());
            return null;
        }


    }
}   // Main Class

