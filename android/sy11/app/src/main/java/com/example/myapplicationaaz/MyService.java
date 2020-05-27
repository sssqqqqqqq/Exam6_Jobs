package com.example.myapplicationaaz;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    //初始化MediaPlay类
    private static  MediaPlayer mediaPlayer =   new MediaPlayer();;
    //设置播放列表
    private List<Music> list = new ArrayList<>();
    //设置初始播放歌曲位置
    private int mindex = 0;
    //设置MyAppWidgetProvider类传递来的动作信息
    public static String ACTION = "to_service";
    public static String KEY_USR_ACTION = "key_usr_action";
    public static final int ACTION_PLAY_PAUSE = 1,ACTION_NEXT = 2,ACTION_LAST = 3;
    private boolean mPlayState = false;


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取广播中的动作信息
            if(mediaPlayer==null){

            }
            String action = intent.getAction();
            System.out.println("---"+action);
            if (ACTION.equals(action)){
                int widget_action = intent.getIntExtra(KEY_USR_ACTION,-1);
                System.out.println(widget_action);
                //根据动作信息执行
                switch (widget_action){
                    case ACTION_LAST:
                        lastSong(context);
                        break;
                    case ACTION_PLAY_PAUSE:
                        if (mPlayState){
                            pause(context);
                        }else {
                            play(context);
                        }
                        break;
                    case ACTION_NEXT:
                        nextSong(context);
                        break;
                    default:
                        break;
                }
            }

        }
    };



    @Override
    public void onCreate() {
        System.out.println("---");
        super.onCreate();
        //动态注册广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        registerReceiver(receiver,intentFilter);
        //初始化播放列表
        initlist();
        //开始播放
        mediaPlayerStart();
    }

    //开始播放方法
    private void mediaPlayerStart(){

        mediaPlayer=MediaPlayer.create(getApplicationContext(), Uri.parse(list.get(mindex).getUrl()));
        mediaPlayer.start();
        mPlayState = true;
    }

    //初始化播放列表的方法
    private void initlist(){
        list = MusicList.getMusicData(getApplicationContext());
        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i).getUrl());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("------");
        super.onDestroy();
        unregisterReceiver(receiver);
        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    //播放下一首的方法
    public void nextSong(Context context){
        System.out.println("-------");
        mPlayState = true;
        mindex++;
        mindex%=list.size();
        playSong(context,1);
    }

    //播放上一首的方法
    public void lastSong(Context context){
        mindex--;
        if(mindex<0){
            mindex=list.size()-1;
        }
        mPlayState = true;
        playSong(context,1);
    }

    //开始播放方法
    public void play(Context context){
        mPlayState = true;
        mediaPlayer.start();
    }

    //暂停方法
    public void pause(Context context){
        mPlayState = false;
        mediaPlayer.pause();
    }

    //当播放上一首下一首的方法
    public void playSong(Context context,int resid){

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(list.get(mindex).getUrl());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}