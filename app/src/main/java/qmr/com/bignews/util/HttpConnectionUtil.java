package qmr.com.bignews.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by qmr on 2016/3/29.
 */
public class HttpConnectionUtil extends AsyncTask<HashMap<String,String>,Void,String> {
    public String url = "http://10.0.2.2:8080/160328/";
    StringBuilder sb;
    @Override
    protected String doInBackground(HashMap... params) {
        url+=params[0].get("servlet")+"?";
        for(Object key:params[1].keySet())
            url = url+key.toString()+params[1].get(key.toString())+"&";
        url = url.substring(0,url.length()-1);
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
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
