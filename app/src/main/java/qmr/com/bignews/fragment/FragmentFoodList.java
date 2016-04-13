package qmr.com.bignews.fragment;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;
import qmr.com.bignews.activity.MainActivity;
import qmr.com.bignews.adapter.FoodListAdapter;
import qmr.com.bignews.model.FoodList;
import qmr.com.bignews.model.FoodListGson;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFoodList extends Fragment {
    ListView listView;
    TextView textView;
    Button button;
    FoodListAdapter adapter;
    int totalPrice = 0;
    NotificationManager manager;

    public static FoodListGson foodListGson;

    public FragmentFoodList() {
        // Required empty public constructor
    }

    Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    textView.setText("总价"+totalPrice);
            }

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_food_list, container, false);

        listView = (ListView) view.findViewById(R.id.lv_food_show);

        textView = (TextView) view.findViewById(R.id.tv_food_price);

        button = (Button) view.findViewById(R.id.btn_food_checkout);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        new getFoodListTask().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                totalPrice+=Integer.parseInt(foodListGson.getRetData().get(position).getFood_price());
                Message message = Message.obtain();
                message.what = 1;
                Log.d("helllo",totalPrice+"");
                mhandler.sendMessage(message);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("确认订单")
                        .setMessage("订单总价为"+totalPrice+"元")
                        .setCancelable(false)
                        .setPositiveButton("确认购买", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(),MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                                Notification.Builder builder1 = new Notification.Builder(getActivity());
                                builder1.setContentTitle("订单已确认")
                                        .setContentText("订单总价为"+totalPrice+"元")
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true)
                                        .setFullScreenIntent(pendingIntent,true)
                                        .setSmallIcon(R.mipmap.ic_launcher) ;
                                manager.notify(1,builder1.build());
                                totalPrice = 0;
                                Message message = Message.obtain();
                                message.what = 1;
                                mhandler.sendMessage(message);

                            }
                        })
                        .setCancelable(false)
                        .setNegativeButton("取消订单", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                totalPrice = 0;
                                Message message = Message.obtain();
                                message.what = 1;
                                mhandler.sendMessage(message);

                            }
                        });
                builder.create().show();
            }
        });

    }


    class getFoodListTask extends AsyncTask<String,Void,String>{
        StringBuilder sb;
        //FoodListGson foodListGson;

        @Override
        protected String doInBackground(String ... params) {
            String url = "http://"+ MyApplication.serviceIP+":8080/160328/Food";
            //url+="username="+params[0]+"&password="+params[1];
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
            Gson gson = new Gson();
            if(sb!=null) {
                FragmentFoodList.foodListGson = gson.fromJson(sb.toString(), FoodListGson.class);
                //Log.d("helllo",FragmentFoodList.foodListGson.getRetData().size()+"");
                return sb.toString();
            }
            else
                return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null) {
                adapter = new FoodListAdapter(getContext(), R.layout.listview_show_food, foodListGson.getRetData());
                listView.setAdapter(adapter);
            }
            else {
                Toast.makeText(getActivity(),"无法连接到服务器",Toast.LENGTH_SHORT).show();
            }

        }
    }


}


