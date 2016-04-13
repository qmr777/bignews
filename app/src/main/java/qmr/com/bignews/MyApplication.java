package qmr.com.bignews;

import android.app.Application;
import android.content.SharedPreferences;

import qmr.com.bignews.fragment.FragmentLogin;

/**
 * Created by qmr on 2016/3/29.
 */
public class MyApplication extends Application {
    public static String serviceIP = "172.20.132.1";
    public static String avdIP = "10.0.2.2";
    public static boolean iflogin = false;
    public static String username;


    @Override
    public void onCreate() {
        super.onCreate();
/*        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        String name = preferences.getString("username","qmr777");
        String pswd = preferences.getString("password","qmr777");
        iflogin = true;
        username = "qmr777";*/

    }


}
