package qmr.com.bignews.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;
import qmr.com.bignews.activity.MyOrderAty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogged extends Fragment {
    View view;
    TextView tv_username;
    ListView listView;
    List<String> stringList = new ArrayList<>();

    public void initLV(){
        stringList.add("我的订单");
        stringList.add("常用联系人");
        stringList.add("关于我们");
    }

    public FragmentLogged() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_logged, container, false);
        tv_username = (TextView) view.findViewById(R.id.tv_logged_username);
        listView = (ListView) view.findViewById(R.id.lv_logged_operation);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    startActivity(new Intent(getActivity(), MyOrderAty.class));
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_username.setText(MyApplication.username);
        if(stringList.isEmpty())
            initLV();
        //listView.setAdapter(new SimpleAdapter(getActivity(),stringList,android.R.layout.simple_list_item_1));
        listView.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,stringList));

    }

}
