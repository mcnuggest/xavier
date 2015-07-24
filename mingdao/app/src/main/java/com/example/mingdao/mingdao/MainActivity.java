package com.example.mingdao.mingdao;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.prefs.PreferenceChangeEvent;


public class MainActivity extends Activity {
    String api = "https://api.mingdao.com/oauth2/access_token?app_Key=1672939984&app_Secret=af036026d51e425d9d17c6cae5d8465a&grant_type=password&format=json&username=xavier.gu@mingdao.com&password=guxin-010";
    StringBuilder urlis = new StringBuilder(api);
    private Button button1;
    private EditText account;
    private EditText key;
    private TextView show;
//    private SharedPreferences pref;
//    private SharedPreferences.Editor editor;
//    private CheckBox rememberPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        pref = PreferenceManager.getDefaultSharedPreferences(this);
//        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        account = (EditText) findViewById(R.id.yonghuming);
        key = (EditText) findViewById(R.id.mima);
        button1 = (Button) findViewById(R.id.login);
        show = (TextView) findViewById(R.id.ceshi);
//        boolean isRemember =pref.getBoolean("remember_password",false);
//        if (isRemember) {
//            String accountEdit = pref.getString("account", "");
//            String passwordEdit = pref.getString("password", "");
//            account.setText(accountEdit);
//            key.setText(passwordEdit);
//            rememberPass.setChecked(true);
//        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*urlis.append("&username=" + account.getText().toString());
                urlis.append("&password=" + key.getText().toString());*/
//                String accountEdit = account.getText().toString();
//                String passwordEdit = key.getText().toString();
//                if(!accountEdit.isEmpty()&&!passwordEdit.isEmpty()){
//                    editor = pref.edit();
//                    if(rememberPass.isChecked()){
//                        editor.putBoolean("remember_password",true);
//                        editor.putString("accountEdit",accountEdit);
//                        editor.putString("passwordEdit",passwordEdit);
//                    }else{
//                        editor.clear();
//                    }
//                }
//                editor.commit();
                InternnetConnect internnetConnect = new InternnetConnect();
                internnetConnect.sendRequestWithHttpURLConnection(urlis.toString(), new ResponseListener() {
                    @Override
                    public void success(final String response) {
                        Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                        intent.putExtra("extra_data", response);
                        Log.d("main", response);
                        startActivity(intent);


                    }

                    @Override
                    public void fail(String response) {

                    }
                });


            }
        });


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
