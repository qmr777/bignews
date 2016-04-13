package qmr.com.bignews.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;
import qmr.com.bignews.model.FoodList;
import qmr.com.bignews.model.FoodListGson;

/**
 * Created by qmr on 2016/3/30.
 */
public class FoodListAdapter extends ArrayAdapter<FoodListGson.RetDataBean> {
    int resourceID;
    FoodListGson.RetDataBean foodList;
    public FoodListAdapter(Context context, int resource, List<FoodListGson.RetDataBean> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        foodList = getItem(position);
        View view;
        ViewHodler viewHodler;
        if(convertView == null){
            viewHodler = new ViewHodler();
            //view = View.inflate(getContext(), R.layout.listview_show_food,null);
            view = LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHodler.imageButton = (ImageButton) view.findViewById(R.id.lvc_foodlist_image);
            viewHodler.textView = (TextView) view.findViewById(R.id.lvc_foodlist_price);
            view.setTag(viewHodler);
        }
        else {
            view = convertView;
            viewHodler = (ViewHodler) view.getTag();
        }


        viewHodler.imageButton.setBackground(null);
        new GetImageTask(viewHodler.imageButton).execute(foodList);
        viewHodler.textView.setText(foodList.getFood_price()+"元   "+foodList.getFood_name());
        return view;
    }

    class ViewHodler{
        ImageButton imageButton;
        TextView textView;
    }



}


class GetImageTask extends AsyncTask<FoodListGson.RetDataBean,Void,Bitmap>{

    public ImageButton ib;
    public GetImageTask(ImageButton imageButton){
        ib = imageButton;
    }

    @Override
    protected Bitmap doInBackground(FoodListGson.RetDataBean... params) {
        //Log.d("helllo","getImage doinbg");
        Bitmap bitmap;
        try {
            URL url = new URL("http://"+ MyApplication.serviceIP+params[0].getFood_image());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            InputStream is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);

            return bitmap;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //Log.d("helllo","getImage onpostexec");
        super.onPostExecute(bitmap);
        ib.setBackground(new BitmapDrawable(bitmap));
    }
}
