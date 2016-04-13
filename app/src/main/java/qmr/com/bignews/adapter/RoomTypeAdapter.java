package qmr.com.bignews.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import qmr.com.bignews.R;
import qmr.com.bignews.model.RoomType;

/**
 * Created by qmr on 2016/4/10.
 */
public class RoomTypeAdapter extends ArrayAdapter<RoomType.RetDataBean> {
    int resourceID;
    RoomType.RetDataBean dataBean;
    public RoomTypeAdapter(Context context, int resource, List<RoomType.RetDataBean> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        dataBean = getItem(position);

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder = new ViewHolder();
            viewHolder.room_type = (TextView) view.findViewById(R.id.tv_room_type);
            viewHolder.room_price = (TextView) view.findViewById(R.id.tv_room_price);
            view.setTag(viewHolder);
            //return view;
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.room_price.setText(dataBean.getRoomPrice());
        viewHolder.room_type.setText(dataBean.getRoomType());
        return view;
    }

    class ViewHolder{
        TextView room_type,room_price;
    }
}
