package com.example.mingdao.mingdao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.TwoStatePreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.logging.LogRecord;

/**
 * Created by mingdao on 15/7/24.
 */
public class Second_Activity extends Activity {
    Context context = this;
    private Spinner spinner = null;
    private ArrayAdapter<String> Spinneradapter = null;
    private static final String[] choice = {"动态更新", "链接", "图片", "文档", "提问"};
    public List<Post> data1 = new ArrayList<>();
    private ListView list;
    String api = "https://api.mingdao.com/post/v2/all?format=json";
    StringBuilder urlis;
    Login login;
    DongTai dongtai;
    Adapter adapter;
    public int type = 0;
    String access_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        spinner = (Spinner) findViewById(R.id.spinner);
        list = (ListView) findViewById(R.id.list);
        Intent intent = getIntent();
        final String[] data = {intent.getStringExtra("extra_data")};
        login = JsonUtil.parseJSONWithGSON(Second_Activity.this, data[0], Login.class);
        access_token = new String(login.getAccess_token());


        list.setAdapter(adapter);
        Spinneradapter = new ArrayAdapter<String>(Second_Activity.this, android.R.layout.simple_spinner_item, choice);
        Spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(Spinneradapter);
        spinner.setVisibility(View.VISIBLE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
                getPosts(type);
                data1.clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Second_Activity.this,"welcome",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPosts(int type){

        urlis = new StringBuilder(api);
        urlis.append("&access_token=" + access_token + "&post_type=" + type);
        Log.d("url", urlis.toString());

        InternnetConnect.sendRequestWithHttpURLConnection(urlis.toString(), new ResponseListener() {
            @Override
            public void success(String response) {
                dongtai = JsonUtil.parseJSONWithGSON(context, response, DongTai.class);
                data1.addAll(dongtai.getPosts());
                adapter = new Adapter(context, data1);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
            }

            @Override
            public void fail(String response) {

            }
        });

    }


}
