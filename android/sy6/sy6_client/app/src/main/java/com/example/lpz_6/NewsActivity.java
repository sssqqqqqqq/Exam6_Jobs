package com.example.lpz_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lpz_6.bean.News;
import com.example.lpz_6.bean.User;
import com.example.lpz_6.config.Config;
import com.example.lpz_6.util.GetPostUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class NewsActivity extends AppCompatActivity {
    class MyHandler extends Handler
    {
        private WeakReference<NewsActivity> mainActivity;
        MyHandler(WeakReference<NewsActivity> mainActivity)
        {
            this.mainActivity = mainActivity;
        }

        @Override
        public void handleMessage(Message msg)
        {

             ListView lv =findViewById(R.id.listview);
            list.clear();
            if (msg.what == 0x123)
            {
                JSONArray ary =(JSONArray) msg.obj;
                for(int i = 0;i<ary.length();i++){
                    try {
                        JSONObject jsonObject = ary.getJSONObject(i);
                        News tmp = new News(jsonObject.getString("title"),
                                jsonObject.getString("date"),
                                jsonObject.getString("category"),
                                jsonObject.getString("author_name"),
                                jsonObject.getString("url"),
                                jsonObject.getString("image"));
                        list.add(tmp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(NewsActivity.this,UrlActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url",list.get(position).getUrl());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                lv.setAdapter(new MyBaseAdapt());
            }
        }
    }

    private Handler handler =  new MyHandler(new WeakReference<>(this));
    private ArrayList<News> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Button button = findViewById(R.id.add);
        button.setOnClickListener(View -> add());
        intiSearch();
        initData("news/get",null);


    }

    public void intiSearch(){
        SearchView searchView = findViewById(R.id.search);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {

            @Override
            public boolean onClose() {
                initData("news/get",null);
                return false;
            }
        });
        searchView.setQueryRefinementEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText);
                initData("news/find","regx="+newText);
                return false;
            }
        });
    }

    public void initData(String url,String params){
        new Thread(() ->
        {

            String str = GetPostUtil.sendGet(
                    Config.BASE_URL+url, params
            );

            try {
                JSONArray jsonObject = new JSONObject(str).getJSONObject("data").getJSONArray("value");
                System.out.println(jsonObject.toString());
                Message msg = new Message();
                msg.what = 0x123;
                msg.obj = jsonObject;
                handler.sendMessage(msg);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }).start();

    }

    public class MyBaseAdapt extends BaseAdapter {
        private class  ViewHolder {
            TextView tv_title;
            TextView tv_date;
            ImageView image;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null){
                v = View.inflate(getApplicationContext(),R.layout.item,null);
                ViewHolder vh = new ViewHolder();
                vh.tv_title = v.findViewById(R.id.cont);
                vh.tv_date = v.findViewById(R.id.date);
                vh.image = v.findViewById(R.id.imageView);
                v.setTag(vh);
            }

            ViewHolder vh = (ViewHolder) v.getTag();
            News news = list.get(position);
            String url = Config.BASE_URL+news.getImage();

            Glide.with(getApplicationContext())
                    .load(url)
                    .into(vh.image);
            vh.tv_title.setText(news.getTitle());
            vh.tv_date.setText(news.getDate());
            return v;
        }


    }
    public void add(){
        Toast.makeText(getApplicationContext(), "你没有权限施行此功能，请联系开发者lpz",
                Toast.LENGTH_LONG).show();
        /*
         * 待补充添加功能
         * */
    }

}
