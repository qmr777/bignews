package qmr.com.bignews.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import qmr.com.bignews.R;
import qmr.com.bignews.fragment.FragmentBookHotel;
import qmr.com.bignews.fragment.FragmentFoodList;
import qmr.com.bignews.fragment.FragmentLogged;
import qmr.com.bignews.fragment.FragmentLogin;

public class MainActivity extends AppCompatActivity implements Logged{
    public static ViewPager viewPager;
    FragmentLogin fragmentLogin;
    FragmentBookHotel fragmentBookHotel;
    FragmentFoodList fragmentFoodList;
    FragmentLogged fragmentLogged;
    TabLayout tabLayout;
    PagerAdapter pagerAdapter;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmentTitle = new ArrayList<>();
    int i = 0;

    public void logged(){
        fragmentLogged = new FragmentLogged();
        fragmentList.remove(fragmentLogin);
        fragmentList.add(fragmentLogged);
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentLogin = new FragmentLogin();
        fragmentBookHotel = new FragmentBookHotel();
        fragmentFoodList = new FragmentFoodList();
        viewPager = (ViewPager) findViewById(R.id.vp_mainAty);
        tabLayout = (TabLayout) findViewById(R.id.tl_mainAty);
        fragmentList.add(fragmentBookHotel);
        fragmentList.add(fragmentFoodList);
        fragmentList.add(fragmentLogin);
        fragmentTitle.add("订房");
        fragmentTitle.add("订餐");
        fragmentTitle.add("我的");
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class PagerAdapter extends FragmentStatePagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

    }
}
