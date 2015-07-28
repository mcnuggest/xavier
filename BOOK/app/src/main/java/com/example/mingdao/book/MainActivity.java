package com.example.mingdao.book;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity implements View.OnClickListener {

    public static final int SHOW_RESPONSE = 0;

    private Button sendRequest;
    private EditText input;
    private TextView translation;
    private TextView translationresult;
    private TextView yinbiao;
    private TextView yinbiaoresult;
    private TextView duanju;
    private ListView list;
    App app;
    private List<App.Web> data;


    /*private Handler handler = new Handler() {
        public  void  handleMessage(Message msg){
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    getResponseText.setText(response);
            };
        }

    };*/
    String url = "http://fanyi.youdao.com/openapi.do?keyfrom=gescscs&key=75589195&type=data&doctype=json&version=1.1&q=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendRequest = (Button) findViewById(R.id.send_request);
        translation = (TextView) findViewById(R.id.translation);
        translationresult = (TextView) findViewById(R.id.translationresult);
        yinbiao = (TextView) findViewById(R.id.yinbiao);
        yinbiaoresult = (TextView) findViewById(R.id.yinbiaoresult);
        duanju = (TextView) findViewById(R.id.duanju);
        list = (ListView) findViewById(R.id.list);
        input = (EditText) findViewById(R.id.input);
        sendRequest.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            StringBuilder urlisr = new StringBuilder(url);
            urlisr.append(input.getText().toString());
            sendRequestWithHttpURLConnection(urlisr.toString());
            translation.setVisibility(View.VISIBLE);
            yinbiao.setVisibility(View.VISIBLE);
            duanju.setVisibility(View.VISIBLE);

        }
    }


    private void sendRequestWithHttpURLConnection(final String url1) {
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
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    String xiahuaxian = new String(response.toString());
                    String save;
                    save = xiahuaxian.replaceAll("-", "_");
                    Log.d("text", save);
                    /*Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    message.obj = response.toString();
                    handler.sendMessage(message);*/
                    parseJSONWithGSON(save);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        app = gson.fromJson(jsonData, new TypeToken<App>() {
        }.getType());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder fanyi = new StringBuilder();
                StringBuilder yinbiao = new StringBuilder();
                if (app.getBasic() != null && !TextUtils.isEmpty(app.getBasic().getUs_phonetic())) {
                    yinbiao.append("美: " + app.getBasic().getUs_phonetic() + "\n");
                }
                if (app.getBasic() != null && !TextUtils.isEmpty(app.getBasic().getUk_phonetic())) {
                    yinbiao.append("英: " + app.getBasic().getUk_phonetic() + "\n");
                }
                if (app.getBasic() != null && !TextUtils.isEmpty(app.getBasic().getExplains().get(0))) {
                    for (int i = 0; i < app.getBasic().getExplains().size(); i++) {
                        fanyi.append(app.getBasic().getExplains().get(i) + "\n");
                    }
                }
                translationresult.setText(fanyi.toString());
                yinbiaoresult.setText(yinbiao.toString());
                list = (ListView) findViewById(R.id.list);

                /*DuanjuAdapter adapter = new DuanjuAdapter(MainActivity.this, R.layout.duan, duanjuList);
                list.setAdapter(adapter);
                duanjuList.clear();
                for (int i = 0; i < app.getWeb().size(); i++) {
                    Duanju duanju = new Duanju(app.getWeb().get(i).getValue(), app.getWeb().get(i).getKey());
                    duanjuList.add(duanju);
                }
                adapter.notifyDataSetChanged();*/
                data = app.getWeb();
                Adapter adapter = new Adapter(MainActivity.this);
                list.setAdapter(adapter);

            }

        });
    }

    static class ViewHolder {

        public TextView value;
        public TextView key;
    }


    public class Adapter extends BaseAdapter {

        private LayoutInflater mInflater = null;

        public Adapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.duan, null);
                holder.key = (TextView) convertView.findViewById(R.id.key);
                holder.value = (TextView) convertView.findViewById(R.id.value);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.key.setText(data.get(position).getKey());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.get(position).getValue().size(); i++) {
                sb.append(data.get(position).getValue().get(i) + "\n");
            }
            holder.value.setText(sb.toString());
            return convertView;
        }


    }
}
