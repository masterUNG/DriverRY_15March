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

public class MyCheckJob extends AsyncTask<Void, Void, String> {

    private Context context;
    private MyConstant myConstant = new MyConstant();
    private String urlPHP = myConstant.getUrlGetJobWhereIdDriverStatus();
    private String idDriverString, statusString;

    public MyCheckJob(Context context, String idDriverString, String statusString) {
        this.context = context;
        this.idDriverString = idDriverString;
        this.statusString = statusString;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("ID_driver", idDriverString)
                    .add("Status", statusString)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("1decV2", "e doIn ==> " + e.toString());
            return null;
        }


    }
}   //Main Class
