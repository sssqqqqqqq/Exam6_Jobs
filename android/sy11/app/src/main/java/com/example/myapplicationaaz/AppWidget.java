package com.example.myapplicationaaz;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

public class AppWidget extends AppWidgetProvider {

    private static  boolean mStop = true;
    private ArrayList<Music> listMusic;
    @Override
    public void onReceive(Context context, Intent intent) {

        // Log.d(this.getClass().getName(), "onReceive");
        String action = intent.getAction();
        listMusic = MusicList.getMusicData(context);
        System.out.println("------"+action);

        if (intent.hasCategory(Intent.CATEGORY_ALTERNATIVE)){
            Uri data = intent.getData();
            int buttonId = Integer.parseInt(data.getSchemeSpecificPart());
            System.out.println("mstop"+" "+mStop);
            switch (buttonId) {
                case R.id.play:
                    System.out.println("1");
                    musicChange(context, MyService.ACTION_PLAY_PAUSE);
                    if (mStop){

                        System.out.println("---");
                        Intent startIntent = new Intent(context,MyService.class);
                        context.startService(startIntent);
                        mStop = false;
                    }
                    break;
                case R.id.stop:
                    System.out.println("1");
                    musicChange(context, MyService.ACTION_PLAY_PAUSE);
                    break;
                case R.id.prev:
                    System.out.println("2");
                    musicChange(context, MyService.ACTION_LAST);
                    break;
                case R.id.next:
                    System.out.println("3");
                    musicChange(context, MyService.ACTION_NEXT);
                    break;
                //


            }
        }

        if (MyService.UI_ACTION.equals(action)){
            int widget_action = intent.getIntExtra(MyService.KEY_UI_ACTION,-1);
            RemoteViews remoteView = new RemoteViews(context.getPackageName(),R.layout.activity_main);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            remoteView.setTextViewText(R.id.titile,listMusic.get(widget_action).getTitle());
            remoteView.setTextViewText(R.id.singer,listMusic.get(widget_action).getSinger());
            remoteView.setTextViewText(R.id.time,timeToString(listMusic.get(widget_action).getTime()));
            ComponentName componentName = new ComponentName(context,AppWidget.class);
            appWidgetManager.updateAppWidget(componentName, remoteView);
        }

        super.onReceive(context, intent);


    }
    private String timeToString(long time){
        time /= 1000;
        long minute = time / 60;
        long second = time % 60;
        minute %= 60;
        return String.format("%02d:%02d", minute, second);
    }
    private void musicChange(Context context, int ACTION) {
        Intent actionIntent = new Intent();
        actionIntent.setAction(MyService.ACTION);
        actionIntent.putExtra(MyService.KEY_USR_ACTION, ACTION);
        context.sendBroadcast(actionIntent);


    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(this.getClass().getName(), "onUpdate");

        listMusic = MusicList.getMusicData(context);
        RemoteViews remoteView = new RemoteViews(context.getPackageName(),R.layout.activity_main);
        remoteView.setOnClickPendingIntent(R.id.play,getPendingIntent(context, R.id.play));
        remoteView.setOnClickPendingIntent(R.id.stop, getPendingIntent(context, R.id.stop));
        remoteView.setOnClickPendingIntent(R.id.prev,getPendingIntent(context, R.id.prev));
        remoteView.setOnClickPendingIntent(R.id.next, getPendingIntent(context, R.id.next));
        remoteView.setTextViewText(R.id.titile,listMusic.get(0).getTitle());
        remoteView.setTextViewText(R.id.singer,listMusic.get(0).getSinger());
        remoteView.setTextViewText(R.id.time,timeToString(listMusic.get(0).getTime()));
        ComponentName componentName = new ComponentName(context,AppWidget.class);
        appWidgetManager.updateAppWidget(componentName, remoteView);
    }

    /**
     * 删除AppWidget
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(this.getClass().getName(), "onDeleted");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(this.getClass().getName(), "onDisabled");
    }

    /**
     * AppWidget首次创建调用
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(this.getClass().getName(), "onEnabled");
    }

    private PendingIntent getPendingIntent(Context context, int buttonId) {
        Intent intent = new Intent();
        intent.setClass(context, AppWidget.class);
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        intent.setData(Uri.parse(""+buttonId));
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pi;
    }


}



