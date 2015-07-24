package com.example.mingdao.mingdao;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by mingdao on 15/7/24.
 */
public class InternnetConnect {
    public void sendRequestWithHttpURLConnection(final String url1, final ResponseListener responseListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(url1);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    final StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            responseListener.success(response.toString());
                        }
                    }) ;
                } catch (MalformedURLException e) {
                    responseListener.fail(e.toString());
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    responseListener.fail(e.toString());
                    e.printStackTrace();
                } catch (IOException e) {
                    responseListener.fail(e.toString());
                    e.printStackTrace();
                }

            }
        }).start();
    }


}

