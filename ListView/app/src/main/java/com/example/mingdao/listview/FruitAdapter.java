package com.example.mingdao.listview;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mingdao on 15/7/22.
 */
public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceID;

    public FruitAdapter(Context context, int textViewResourceID, List<Fruit> objects) {
        super(context, textViewResourceID, objects);
        resourceID = textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        TextView fruitname = (TextView) view.findViewById(R.id.fruit_name);
        fruitname.setText(fruit.getName());
        return view;
    }

}
