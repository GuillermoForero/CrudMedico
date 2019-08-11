package com.me.crudmedico.data.remote;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.Socket;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Client extends AsyncTask<Void, Void, String> {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    String response = "";
    Context context;
    OkHttpClient client = new OkHttpClient();

    public Client(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Socket socket;
        try {
            post("http://localhost:4567/hello/Jolanda");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    String post(String url) throws IOException {
        //RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}