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
 * Created by masterUNG on 2/15/2017 AD.
 */

public class FindStatusDriver extends AsyncTask<String, Void, String>{

    private Context context;
    private MyConstant myConstant = new MyConstant();
    private String urlPHP = myConstant.getUrlGetStatusWhereIdUser();

    public FindStatusDriver(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("id", strings[0])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("15febV1", "e doin ==> " + e.toString());
            return null;
        }


    }
}   // Main Class
