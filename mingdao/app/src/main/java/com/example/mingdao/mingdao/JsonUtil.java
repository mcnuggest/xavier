package com.example.mingdao.mingdao;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by mingdao on 15/7/24.
 */
public class JsonUtil {
    public static <T> T parseJSONWithGSON(Context context, String jsonData,Class<T> clazz) {
        Gson gson = new Gson();
//        T object = gson.fromJson(jsonData, TypeToken.get(clazz));
        T object = gson.fromJson(jsonData,clazz);
        if (object != null) {

            return object;
        } else {
            Toast.makeText(context, "Wrong!", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}
