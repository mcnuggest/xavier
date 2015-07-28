package com.example.mingdao.mingdao;

import android.app.Activity;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.LogRecord;

/**
 * Created by mingdao on 15/7/24.
 */
public class Second_Activity extends Activity {

    private Spinner spinner = null;
    private ArrayAdapter<String> adapter = null;
    private static final String[] choice = {"动态", "更新", "链接", "图片", "文档"};
    private List<Post> data1 = new ArrayList<>();
    private ListView list;
    String api = "https://api.mingdao.com/post/v2/all?format=json";
    StringBuilder urlis = new StringBuilder(api);
    Login login;
    DongTai dongtai;

    ViewHolder holder = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Intent intent = getIntent();
        final String[] data = {intent.getStringExtra("extra_data")};
        login = JsonUtil.parseJSONWithGSON(Second_Activity.this, data[0], Login.class);
        String access_token = new String(login.getAccess_token());
        urlis.append("&access_token=" + access_token);
        Log.d("main", urlis.toString());

        InternnetConnect internnetConnect = new InternnetConnect();
        internnetConnect.sendRequestWithHttpURLConnection(urlis.toString(), new ResponseListener() {
            @Override
            public void success(final String response) {
                dongtai = JsonUtil.parseJSONWithGSON(Second_Activity.this, response, DongTai.class);
                data1.addAll(dongtai.getPosts());
                Adapter adapter = new Adapter(Second_Activity.this);
                list.setAdapter(adapter);


            }

            @Override
            public void fail(String response) {

            }
        });


        spinner = (Spinner) findViewById(R.id.spinner);
        list = (ListView) findViewById(R.id.list);
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

    static class ViewHolder {

        public TextView name;
        public TextView time;
        public TextView text;
        public TextView tag;
        public ImageView avatar;

    }

    public class Adapter extends BaseAdapter {

        private LayoutInflater mInflater = null;
        Bitmap bitmap;
        public Adapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data1.size();
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
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.dongtai, null);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.tag = (TextView) convertView.findViewById(R.id.tags);
                holder.avatar = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final Post post = data1.get(position);
            String imageurl = post.getUser().getAvatar();
            Log.d("image",imageurl);
            ImageLoader.getInstance().loadImage(imageurl,new SimpleImageLoadingListener(){
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    holder.avatar.setImageBitmap(loadedImage);
                }
            });



            holder.name.setText(data1.get(position).getUser().getName());
            holder.time.setText(data1.get(position).getCreate_time());
            holder.text.setText(data1.get(position).getText());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data1.get(position).getTags().size(); i++) {
                sb.append(data1.get(position).getTags().get(i).getName());
            }
            holder.tag.setText(sb.toString());

            return convertView;
        }
    }
}
