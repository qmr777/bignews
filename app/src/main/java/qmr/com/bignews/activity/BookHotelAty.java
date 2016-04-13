package qmr.com.bignews.activity;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import qmr.com.bignews.MyApplication;
import qmr.com.bignews.R;
import qmr.com.bignews.adapter.RoomTypeAdapter;
import qmr.com.bignews.model.RoomType;
import qmr.com.bignews.task.BookHotelTask;

public class BookHotelAty extends AppCompatActivity {
    boolean selectDate = false;
    CalendarView calendarView;
    Button apply;
    TextView in,out;
    EditText person_name,person_id;
    int yyyy,mm,dd;
    RoomType roomType;
    RoomType.RetDataBean dataBean;
    ListView listView;
    String dateNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss",Locale.CHINA);
        //String dateNow = myformat.format(System.currentTimeMillis());
        setContentView(R.layout.activity_book_hotel_aty);

        listView = (ListView) findViewById(R.id.lv_hotel_type);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        apply = (Button) findViewById(R.id.btn_apply_book_hotel);
        in = (TextView) findViewById(R.id.tv_intime_bh);
        out = (TextView) findViewById(R.id.tv_outtime_bh);
        person_id = (EditText) findViewById(R.id.et_person_id_bh);
        person_name = (EditText) findViewById(R.id.et_person_name_bh);

        new GetHotelTask().execute();
        long minDate = System.currentTimeMillis();
        long maxDate = minDate+30*24*60*60*1000L;
        calendarView.setMinDate(minDate);
        calendarView.setMaxDate(maxDate);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                if(!selectDate){
                    if(in.getText().toString().isEmpty()){
                        in.setText(year+"-"+month+"-"+dayOfMonth);
                        yyyy = year;
                        mm = month;
                        dd = dayOfMonth;
                        Log.d("helllo",calendarView.getDate()+"");
                    }
                    else if((year<yyyy)
                            ||(year == yyyy && month>mm)
                            ||(yyyy == year && mm == month && dayOfMonth>dd)) {
                        out.setText(year+"-"+month+"-"+dayOfMonth);
                        selectDate = true;
                    }
                    else
                        Toast.makeText(getApplicationContext(),"离店日期必须大于入住日期",Toast.LENGTH_SHORT).show();
                }
                else if(selectDate){
                    selectDate = false;
                    yyyy = 0;
                    dd = 0;
                    mm = 0;
                    in.setText("");
                    out.setText("");
                    Toast.makeText(getApplicationContext(),"重新选择入住日期",Toast.LENGTH_SHORT).show();
                }
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if((!in.getText().toString().isEmpty())
                        &&(!out.getText().toString().isEmpty())
                        &&(dataBean!=null)
                        &&(!person_id.getText().toString().isEmpty())
                        &&(!person_name.getText().toString().isEmpty())){
                    JSONObject jsonObject = new JSONObject();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
                    int total_cost = 0;
                    try {
                        Date date1 = dateFormat.parse(in.getText().toString());
                        Date date2 = dateFormat.parse(out.getText().toString());
                        total_cost = getIntervalDays(date1,date2)*Integer.parseInt(dataBean.getRoomPrice());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    try {
                        jsonObject.put("errcode",0);
                        jsonObject.put("start_time",in.getText().toString());
                        jsonObject.put("end_time",out.getText().toString());
                        jsonObject.put("room_type",dataBean.getRoomType());
                        jsonObject.put("room_price",dataBean.getRoomPrice());
                        jsonObject.put("total_cost",total_cost+"");
                        dateNow = myformat.format(System.currentTimeMillis());
                        jsonObject.put("person_name",person_name.getText().toString());
                        jsonObject.put("person_id",person_id.getText().toString());
                        jsonObject.put("order_date",dateNow);
                        jsonObject.put("user_name",MyApplication.username);
                        //Log.d("helllo","bookhotelAty");
                        new BookHotelTask(BookHotelAty.this).execute(jsonObject.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                    Toast.makeText(BookHotelAty.this,"信息不全",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public int getIntervalDays(Date startday,Date endday){
        if(startday.after(endday)){
            Date cal=startday;
            startday=endday;
            endday=cal;
        }
        long sl=startday.getTime();
        long el=endday.getTime();
        long ei=el-sl;
        return (int)(ei/(1000*60*60*24));
    }

    class GetHotelTask extends AsyncTask<Void,Void,Void>{
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(BookHotelAty.this);
            dialog.setCancelable(false);
            dialog.setMessage("正在加载");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                Gson gson = new Gson();
                String url = "http://"+ MyApplication.serviceIP+":8080/160328/RoomType";
                Log.d("helllo",url);
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder stringBuilder = new StringBuilder();
                String s;
                while ((s = reader.readLine())!= null)
                    stringBuilder.append(s);
                reader.close();
                Log.d("helllo",stringBuilder.toString());
                roomType = gson.fromJson(stringBuilder.toString(),RoomType.class);
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            //Toast.makeText(BookHotelAty.this,roomType.getRetData().size()+"",Toast.LENGTH_SHORT).show();
            RoomTypeAdapter adapter = new RoomTypeAdapter(BookHotelAty.this,R.layout.listview_show_room,roomType.getRetData());
            listView.setMinimumHeight(roomType.getRetData().size()*20);
            listView.setAdapter(adapter);
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                View v;
                Drawable background;
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dataBean = roomType.getRetData().get(position);
                    //parent.setBackground(BookHotelAty.this.getResources().getDrawable(R.color.colorWhite));
                    if (v!= null) {
                        v.setBackground(background);
                    }
                    else
                        background = view.getBackground();
                    view.setBackground(BookHotelAty.this.getResources().getDrawable(R.color.colorSelect));
                    v = view;
                }
            });
        }
    }
}
