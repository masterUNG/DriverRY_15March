package com.akexorcist.googledirection.sample;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterUNG on 12/29/2016 AD.
 */

public class EditTimeArrive extends AsyncTask<Void, Void, String>{

    //Explicit
    private MyConstant myConstant = new MyConstant();
    private String urlPHP = myConstant.getUrlEditTimeArriveWhereId();
    private Context context;
    private String idDriverString, statusString, timeArriveString, startCountString;

    public EditTimeArrive(Context context,
                          String idDriverString,
                          String statusString,
                          String timeArriveString,
                          String startCountString) {
        this.context = context;
        this.idDriverString = idDriverString;
        this.statusString = statusString;
        this.timeArriveString = timeArriveString;
        this.startCountString = startCountString;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("ID_driver", idDriverString)
                    .add("Status", statusString)
                    .add("TimeArrive", timeArriveString)
                    .add("StartCountTime", startCountString)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
}   // Main Class