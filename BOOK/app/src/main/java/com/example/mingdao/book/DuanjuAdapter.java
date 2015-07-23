package com.example.mingdao.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mingdao on 15/7/23.
 */
public class DuanjuAdapter extends ArrayAdapter<Duanju> {

    private int id;

    public DuanjuAdapter(Context context, int textViewResourceId, List<Duanju> objects) {
        super(context, textViewResourceId, objects);
        id = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Duanju duanju = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(id, null);
        TextView key = (TextView) view.findViewById(R.id.key);
        TextView value = (TextView) view.findViewById(R.id.value);
        key.setText(duanju.getKey());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < duanju.getValue().size(); i++) {
            sb.append(duanju.getValue().get(i) + "\n");
        }
        value.setText(sb.toString());
        return view;
    }
}
