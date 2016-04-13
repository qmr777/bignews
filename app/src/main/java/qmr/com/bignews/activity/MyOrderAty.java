package qmr.com.bignews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;
import qmr.com.bignews.adapter.MyOrderAdapter;
import qmr.com.bignews.task.GetMyOrderTask;

public class MyOrderAty extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_aty);
        listview = (ListView) findViewById(R.id.lv_my_order);
        new GetMyOrderTask(this,listview).execute(MyApplication.username);
    }
}
