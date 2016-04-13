package qmr.com.bignews.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.codeboy.android.cycleviewpager.CycleViewPager;
import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;
import qmr.com.bignews.activity.BookHotelAty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBookHotel extends Fragment {
    ViewPager viewPager;
    View view;
    List<Bitmap> bitmapList = new ArrayList<>();
    List<ImageView> imageViewList = new ArrayList<>();
    Button bookHotel;
    int i = 0;

    Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(i%imageViewList.size());
            i++;
        }
    };

    Timer timer = new Timer();


    public FragmentBookHotel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_book_hotel, container, false);
        bookHotel = (Button) view.findViewById(R.id.btn_book_mainFrag);
        viewPager = (ViewPager) view.findViewById(R.id.vp_book_mainFrag);
        return view;
    }

    void initview(){

        bitmapList.add(BitmapFactory.decodeResource(getResources(),R.drawable.ershiyibijiu2));
        bitmapList.add(BitmapFactory.decodeResource(getResources(),R.drawable.ershiyibijiu3));
        bitmapList.add(BitmapFactory.decodeResource(getResources(),R.drawable.ershiyi2));
        bitmapList.add(BitmapFactory.decodeResource(getResources(),R.drawable.ershiyi3));

        for(int i = 0;i<bitmapList.size();i++){
            ImageView imageView = new ImageView(getActivity());
            //imageView.setLayoutParams(new ViewPager.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT,
            // ViewPager.LayoutParams.MATCH_PARENT));
            imageView.setBackground(new BitmapDrawable(bitmapList.get(i)));
            imageViewList.add(imageView);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(imageViewList.size() == 0)
            initview();

        viewPager.setAdapter(new MyAdapter());
        timer.schedule(new ImageChangeTask(),5000,5000);

        bookHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MyApplication.iflogin)
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(getActivity(), BookHotelAty.class));
            }
        });

    }

    class ImageChangeTask extends TimerTask{

        @Override
        public void run() {
            mhandler.sendMessage(Message.obtain());
        }
    }

    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
            object = null;
        }
    }
}
