package qmr.com.bignews.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.net.ssl.HttpsURLConnection;

import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;
import qmr.com.bignews.activity.Logged;
import qmr.com.bignews.activity.MainActivity;
import qmr.com.bignews.activity.Register;

/**
 * Created by qmr on 2016/3/29.
 * 负责登录部分，如果成功就将当前用户名/密码存在data.xml中
 * 并将application中的username设为用户名，loginStatus设为true
 */
public class FragmentLogin extends Fragment{

    View view;
    EditText username,password;
    Button login;
    TextView lostPswd,register;

    @Override
    public Context getContext() {
        return super.getContext();
    }

    public class connectTask extends AsyncTask<String,Void,String>{
        StringBuilder sb;
        @Override
        protected String doInBackground(String... params) {
            String url = "http://"+ MyApplication.serviceIP+":8080/160328/Login?";
            url+="username="+params[0]+"&password="+params[1];
            Log.d("helllo",url);
            try {
                URL httpUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();
                InputStream is = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                sb = new StringBuilder();
                String s;
                while ((s = reader.readLine()) != null) {
                    sb.append(s);
                }
                Log.d("helllo",sb.toString());
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            //Log.d("helllo",strRead+" if null");
            //return sb.toString();
            if(sb == null)
                return "null";
            else
                return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.get("errcode").equals("0")){
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                    editor.putString("username",username.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.apply();
                    MyApplication.iflogin = true;
                    MyApplication.username = username.getText().toString();
                    MainActivity.viewPager.setCurrentItem(0);
                    ((Logged)getActivity()).logged();
                    //FragmentLogged.initLV();
                }
                else {
                    Toast.makeText(getContext(),jsonObject.getString("result"),Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login,null);
        username = (EditText) view.findViewById(R.id.et_login_username);
        password = (EditText) view.findViewById(R.id.et_login_password);
        login = (Button) view.findViewById(R.id.btn_login_login);
        lostPswd = (TextView) view.findViewById(R.id.tv_login_lostpswd);
        register = (TextView) view.findViewById(R.id.tv_login_register);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            private int cou = 0;
            int selectionEnd = 0;
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                cou = before + count;
                String editable = username.getText().toString();
                String str = StringFilter(editable); //过滤特殊字符
                if (!editable.equals(str)) {
                    username.setText(str);
                }
                username.setSelection(username.length());
                cou = username.length();
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (cou > 14) {
                selectionEnd = username.getSelectionEnd();
                s.delete(14, selectionEnd);
            }
        }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")
                        ||password.getText().toString().equals(""))
                    Toast.makeText(getContext(),"用户名/密码不允许为空",Toast.LENGTH_SHORT).show();
                else {
                    new connectTask().execute(username.getText().toString(),password.getText().toString());
                }
            }
        });
    }

    public static String StringFilter(String str)throws PatternSyntaxException {
        String regEx = "[/\\:*?<>|\"\n\t]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
