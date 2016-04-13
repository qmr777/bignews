package qmr.com.bignews.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;

public class Register extends AppCompatActivity {

    EditText username,password,repassword,phone,idnum;
    TextView textView;
    Button button;

    ProgressDialog dialog;

    class RegisterTask extends AsyncTask<String,Void,String>{

        StringBuilder sb;

        @Override
        protected String doInBackground(String... params) {
            String url = "http://"+ MyApplication.serviceIP+":8080/160328/Register?";
            url+="username="+params[0]+"&password="+params[1]+"&phonenum="+params[2]+"&idnum="+params[3];
            Log.d("helllo",url);
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("POST");
                connection.connect();
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                sb = new StringBuilder();
                String s;
                while ((s = reader.readLine()) != null) {
                    sb.append(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("helllo",s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.get("errcode").equals("0"))
                    Toast.makeText(Register.this,"注册成功！",Toast.LENGTH_SHORT).show();
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("注册失败")
                            .setMessage(jsonObject.getString("result"))
                            .setCancelable(false)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定",null);
                    builder.create().show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.cancel();


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.et_register_username);
        password = (EditText) findViewById(R.id.et_register_password);
        repassword = (EditText) findViewById(R.id.et_register_repassword);
        phone = (EditText) findViewById(R.id.et_register_phonenum);
        idnum = (EditText) findViewById(R.id.et_register_idnum);
        textView = (TextView) findViewById(R.id.tv_register_hint);
        button = (Button) findViewById(R.id.btn_register_register);

        repassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!password.getText().toString().equals(repassword.getText().toString()))
                        textView.setVisibility(View.VISIBLE);
                }
                if(hasFocus)
                    textView.setVisibility(View.INVISIBLE);
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!password.getText().toString().equals(repassword.getText().toString()))
                        textView.setVisibility(View.VISIBLE);
                }
                if(hasFocus)
                    textView.setVisibility(View.INVISIBLE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.getText().toString().equals(repassword.getText().toString())){

                }

                else if(username.getText().toString().equals("")
                        ||password.getText().toString().equals("")
                        ||repassword.getText().toString().equals("")
                        ||phone.getText().toString().equals("")
                        ||idnum.getText().toString().equals("")
                        )
                    Toast.makeText(getApplicationContext(),"还有未填项",Toast.LENGTH_LONG).show();
                else {
                    dialog = new ProgressDialog(Register.this);
                    dialog.setTitle("注册中...");
                    dialog.setMessage("^_^");
                    dialog.show();
                    new RegisterTask().execute(username.getText().toString(), password.getText().toString(),
                            phone.getText().toString(), idnum.getText().toString());
                }
            }
        });
    }
}
