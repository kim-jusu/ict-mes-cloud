package com.example.user.ddnas_example;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by user on 2016-11-26.
 */

public class NetworkTask extends AsyncTask<Map<String, String>, Integer, String> {
    String url;
    Data data;
    String result = null;

    public NetworkTask(String url)
    {
        this.url = url;
    }

    @Override
    protected String doInBackground(Map<String, String>... maps) { // 내가 전송하고 싶은 파라미터

        // Http 요청 준비 작업
        HttpClient.Builder http = new HttpClient.Builder("POST", "http://192.168.0.14/" + url);
        //URL /android/

        // Parameter 를 전송한다.
        http.addAllParameters(maps[0]);


        //Http 요청 전송
        HttpClient post = http.create();
        post.request();

        // 응답 상태코드 가져오기
        int statusCode = post.getHttpStatusCode();

        // 응답 본문 가져오기
        String body = post.getBody();
        return body;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}