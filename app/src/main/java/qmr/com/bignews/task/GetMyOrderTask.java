package qmr.com.bignews.task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;
import qmr.com.bignews.adapter.MyOrderAdapter;
import qmr.com.bignews.model.OrderInfo;

/**
 * Created by qmr on 2016/4/12.
 */
public class GetMyOrderTask extends AsyncTask<String,Void,String> {
    ListView listView;
    Context context;

    public GetMyOrderTask(Activity activity,ListView listView){
        this.listView = listView;
        context = activity;
    }
    @Override
    protected String doInBackground(String... params) {
        String username = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection)
                    new URL("http://" + MyApplication.serviceIP + ":8080/160328/GetOrder").openConnection();
            connection.setRequestMethod("POST");
            OutputStream outputStream = connection.getOutputStream();
            //Log.d("helllo",connection.getResponseMessage());
            outputStream.write(username.getBytes());
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String s;
            while ((s = reader.readLine())!=null){
                builder.append(s);
            }
            is.close();
            reader.close();
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s == null){

        }
        else {
            OrderInfo info = new Gson().fromJson(s,OrderInfo.class);
            MyOrderAdapter adapter = new MyOrderAdapter(context, R.layout.listview_show_order,info.getRetData());
            listView.setAdapter(adapter);
        }
    }
}
