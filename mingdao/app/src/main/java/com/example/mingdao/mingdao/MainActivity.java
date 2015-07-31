package com.example.mingdao.mingdao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;


public class MainActivity extends Activity {
    String api = "https://api.mingdao.com/oauth2/access_token?app_Key=1672939984&app_Secret=af036026d51e425d9d17c6cae5d8465a&grant_type=password&format=json";
    StringBuilder urlis = new StringBuilder(api);
    Context context =this;
    private Button button1;
    private EditText account;
    private EditText key;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor ;
    private CheckBox rememberPass;
    Login login;
    private int check = 1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        account = (EditText) findViewById(R.id.yonghuming);
        key = (EditText) findViewById(R.id.mima);
        button1 = (Button) findViewById(R.id.login);

        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String accountEdit = pref.getString("accountEdit", "");
            String passwordEdit = pref.getString("passwordEdit", "");
            account.setText(accountEdit);
            key.setText(passwordEdit);

            rememberPass.setChecked(true);
        }
        key.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    if (check == 1) {
                        urlis.append("&username=" + account.getText().toString());
                        urlis.append("&password=" + key.getText().toString());
                        check ++;
                    }
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("Loadding");
                    progressDialog.setMessage("读取中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    final String accountEdit = account.getText().toString();
                    final String passwordEdit = key.getText().toString();
                    InternnetConnect internnetConnect = new InternnetConnect();
                    internnetConnect.sendRequestWithHttpURLConnection(urlis.toString(), new ResponseListener() {
                        @Override
                        public void success(final String response) {
                            login = JsonUtil.parseJSONWithGSON(MainActivity.this, response.toString(), Login.class);
                            if (!TextUtils.isEmpty(login.getAccess_token())) {
                                editor = pref.edit();
                                if (rememberPass.isChecked()) {
                                    editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                                    editor.putBoolean("remember_password", true);
                                    editor.putString("accountEdit", accountEdit);
                                    editor.putString("passwordEdit", passwordEdit);
                                } else {
                                    editor.clear();
                                }
                                editor.commit();
                                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                                intent.putExtra("extra_data", response);
                                Log.d("main", response);

                                startActivity(intent);
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"输入有误",Toast.LENGTH_SHORT).show();
                                check=1;
                                urlis = new StringBuilder(api);

                            }

                        }

                        @Override
                        public void fail(String response) {

                        }
                    });
                    return true;
                }
                return false;
            }

        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == 1) {
                    urlis.append("&username=" + account.getText().toString());
                    urlis.append("&password=" + key.getText().toString());
                    check++;
                }


                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Loadding");
                progressDialog.setMessage("读取中");
                progressDialog.setCancelable(false);
                progressDialog.show();


                final String accountEdit = account.getText().toString();
                final String passwordEdit = key.getText().toString();
                InternnetConnect internnetConnect = new InternnetConnect();
                internnetConnect.sendRequestWithHttpURLConnection(urlis.toString(), new ResponseListener() {
                    @Override
                    public void success(final String response) {
                        login = JsonUtil.parseJSONWithGSON(MainActivity.this, response.toString(), Login.class);
                        if (!TextUtils.isEmpty(login.getAccess_token())) {
                            editor = pref.edit();
                            if (rememberPass.isChecked()) {
                                editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                                editor.putString("access_token", login.getAccess_token());
                                editor.putBoolean("remember_password", true);
                                editor.putString("accountEdit", accountEdit);
                                editor.putString("passwordEdit", passwordEdit);
                            } else {
                                editor.clear();
                            }
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                            intent.putExtra("extra_data", response);
                            Log.d("main", response);

                            startActivity(intent);
//                            progressDialog.cancel();
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "输入有误", Toast.LENGTH_SHORT).show();
                            check = 1;
                            urlis = new StringBuilder(api);

                        }
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
