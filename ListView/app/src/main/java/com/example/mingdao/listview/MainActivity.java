package com.example.mingdao.listview;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
//    private String[] data = { "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango" };
    private List<Fruit>fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.second,fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void initFruits(){
        Fruit apple = new Fruit("apple");
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana");
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange");
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon");
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear");
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape");
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple");
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry");
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry");
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango");
        fruitList.add(mango);


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
}
