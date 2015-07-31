package com.example.mingdao.mingdao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mingdao on 15/7/30.
 */
public class LikeIt {
    private SharedPreferences pref ;
    private SharedPreferences.Editor editor;
    private String url = "https://api.mingdao.com/post/add_like";
    private StringBuffer urlsb ;
    public void LikeIt(final Context context,String id){
        urlsb = new StringBuffer(url);
        pref = context.getSharedPreferences("data",context.MODE_PRIVATE);
        urlsb.append("?access_token="+pref.getString("access_token",""));
        urlsb.append("&p_id="+id);
        Log.d("tijiao", urlsb.toString());
        InternnetConnect.sendRequestWithHttpURLConnection(urlsb.toString(), new ResponseListener() {
            @Override
            public void success(String response) {
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void fail(String response) {

            }
        });
    }

}
