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
 * Created by masterUNG on 12/2/2016 AD.
 */

public class EditStatusTo2 extends AsyncTask<Void, Void, String> {

    //Explicit
    private MyConstant myConstant = new MyConstant();
    private String urlJSON = myConstant.getUrlEditStatusWhereId();
    private Context context;
    private String idDriverString;
    private String status_oldString;
    private String statusString;

    public EditStatusTo2(Context context,
                         String idDriverString,
                         String status_oldString,
                         String statusString) {
        this.context = context;
        this.idDriverString = idDriverString;
        this.status_oldString = status_oldString;
        this.statusString = statusString;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("ID_driver", idDriverString)
                    .add("Status_old", status_oldString)
                    .add("Status", statusString)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlJSON).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            Log.d("2decV2", "e doIn ==> " + e.toString());
            return null;
        }


    }
}   // Main Class
