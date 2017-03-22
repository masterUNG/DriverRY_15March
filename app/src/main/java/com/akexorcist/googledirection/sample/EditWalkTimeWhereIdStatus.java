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
 * Created by masterUNG on 3/22/2017 AD.
 */

public class EditWalkTimeWhereIdStatus extends AsyncTask<String, Void, String>{

    private static final String urlPHP = "http://swiftcodingthai.com/ry/editWalkTimeWhereIdStatus.php";
    private Context context;

    public EditWalkTimeWhereIdStatus(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("ID_driver", strings[0])
                    .add("WalkTime", strings[1])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("22MarchV3", "e doIn ==> " + e.toString());
            return null;
        }


    }
}   // Main Class
