package com.example.mingdao.change;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by mingdao on 15/7/21.
 */

public class SecondActivity extends Activity {
    private TextView textView1;
    String api = "https://api.mingdao.com/post/v2/all?format=json";
    StringBuilder stb = new StringBuilder(api);
    /*private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;

            }
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        textView1 = (TextView) findViewById(R.id.response);
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        try {
            JSONObject jsonObject = new JSONObject(data);
            String access_token = jsonObject.getString("access_token");
//            String expires_in = jsonObject.getString("expires_in");
//            String refresh_token = jsonObject.getString("refresh_token");
            stb.append("&access_token=" + access_token);
            Log.d("text",stb.toString());
           // textView1.setText(stb.toString());
            /*stb.append("access_token is " + access_token+"\n");
            stb.append("expires_in is " + expires_in+"\n");
            stb.append("refresh_token is " + refresh_token+"\n");
            textView1.setText(stb.toString());*/
        } catch (JSONException e1) {
            e1.printStackTrace();}
        catch (Exception e) {
            e.printStackTrace();
        }
        Get(stb.toString());

        //parseJSONWithJSONObject(data);



    }
    private  void Get(final String urlstr){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(urlstr);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    final StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView1.setText(response.toString());
                        }
                    });
                    /*Message msg = new Message();
                    msg.what = SHOW_RESPONSE;
                    msg.obj = response.toString();
                    handler.sendMessage(msg);*/


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
    /*private void parseJSONWithJSONObject(String jsonData){
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String access_token = jsonObject.getString("access_token");
            String expires_in = jsonObject.getString("expires_in");
            String refresh_token = jsonObject.getString("refresh_token");
            stb.append("access_token is " + access_token+"\n");
            stb.append("expires_in is " + expires_in+"\n");
            stb.append("refresh_token is " + refresh_token+"\n");
            textView1.setText(stb.toString());

            } catch (JSONException e1) {
            e1.printStackTrace();}
            catch (Exception e) {
            e.printStackTrace();
            }

    }*/
}

