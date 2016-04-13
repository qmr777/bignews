package qmr.com.bignews.task;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;
import qmr.com.bignews.activity.BookHotelAty;
import qmr.com.bignews.activity.MainActivity;

/**
 * Created by qmr on 2016/4/11.
 */
public class BookHotelTask extends AsyncTask<String, Void, String> {
    Context context;
    public BookHotelTask(Activity activity) {
        context = activity;
    }
    ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("正在订房...");
        dialog.setCancelable(false);
        dialog.show();

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            JSONObject jsonObject = new JSONObject(params[0]);
            HttpURLConnection connection = (HttpURLConnection)
                    new URL("http://" + MyApplication.serviceIP + ":8080/160328/BookHotel").openConnection();
            connection.setRequestMethod("POST");
            OutputStream outputStream = connection.getOutputStream();
            //Log.d("helllo",connection.getResponseMessage());
            outputStream.write(jsonObject.toString().getBytes());
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
        dialog.dismiss();
        if(s.equals("success")){
            Toast.makeText(context,"成功",Toast.LENGTH_SHORT).show();
            ((Activity)context).finish();
        }

    }
}
