package com.example.mingdao.mingdao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

/**
 * Created by mingdao on 15/7/24.
 */
public class Second_Activity extends Activity {
    private TextView result = null;
    private Spinner spinner = null;
    private ArrayAdapter<String> adapter = null;
    private static final String[] choice = {"动态更新", "链接", "图片", "文档"};
    String api = "https://api.mingdao.com/post/v2/all?format=json";
    StringBuilder urlis = new StringBuilder(api);
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");



        login = JsonUtil.parseJSONWithGSON(Second_Activity.this, data,Login.class);
        String access_token = new String(login.getAccess_token());
        urlis.append("&access_token=" + access_token);
        Log.d("main",urlis.toString());
        InternnetConnect internnetConnect = new InternnetConnect();
        internnetConnect.sendRequestWithHttpURLConnection(urlis.toString(), new ResponseListener() {
            @Override
            public void success(final String response) {
                result.setText(response);


            }

            @Override
            public void fail(String response) {

            }
        });


        result = (TextView) findViewById(R.id.result);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.VISIBLE);


//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }
}
