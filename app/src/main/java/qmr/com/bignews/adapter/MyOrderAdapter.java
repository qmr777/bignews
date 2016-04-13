package qmr.com.bignews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import qmr.com.bignews.R;
import qmr.com.bignews.model.OrderInfo;

/**
 * Created by qmr on 2016/4/12.
 */

public class MyOrderAdapter extends ArrayAdapter<OrderInfo.RetDataBean> {
    int resourceID;
    public MyOrderAdapter(Context context, int resource, List<OrderInfo.RetDataBean> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        OrderInfo.RetDataBean bean = getItem(position);
        ViewHolder viewholder;
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewholder = new ViewHolder();
            viewholder.tv_in_time = (TextView) view.findViewById(R.id.tv_in_time);
            viewholder.tv_out_time = (TextView) view.findViewById(R.id.tv_out_time);
            viewholder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            viewholder.tv_order_time = (TextView) view.findViewById(R.id.tv_order_time);
            viewholder.tv_room_type = (TextView) view.findViewById(R.id.tv_room_type);
            viewholder.tv_total_price = (TextView) view.findViewById(R.id.tv_total_price);
            view.setTag(viewholder);
        }
        else {
            view = convertView;
            viewholder = (ViewHolder) convertView.getTag();
        }

        viewholder.tv_in_time.setText(bean.getStart_time());
        viewholder.tv_out_time.setText(bean.getEnd_time());
        viewholder.tv_order_time.setText(bean.getOrder_date());
        viewholder.tv_room_type.setText(bean.getRoom_type());
        viewholder.tv_total_price.setText(bean.getTotal_cost());
        viewholder.tv_name.setText(bean.getPerson_name());

        return view;

    }

    class ViewHolder{
        TextView tv_name,
        tv_order_time,
        tv_in_time,
        tv_out_time,
        tv_room_type,
        tv_total_price;
    }
}
