package com.example.myapplicationaaz;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
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

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        // Log.d(this.getClass().getName(), "onReceive");
        String action = intent.getAction();
        List<Music> list = new ArrayList<>();


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

            }
        }

        // 停止播放

    }
    private void musicChange(Context context, int ACTION) {
        Intent actionIntent = new Intent();
        actionIntent.setAction(MyService.ACTION);
        actionIntent.putExtra("key_usr_action", ACTION);
        context.sendBroadcast(actionIntent);


    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(this.getClass().getName(), "onUpdate");

        RemoteViews remoteView = new RemoteViews(context.getPackageName(),R.layout.activity_main);
        remoteView.setOnClickPendingIntent(R.id.play,getPendingIntent(context, R.id.play));
        remoteView.setOnClickPendingIntent(R.id.stop, getPendingIntent(context, R.id.stop));
        remoteView.setOnClickPendingIntent(R.id.prev,getPendingIntent(context, R.id.prev));
        remoteView.setOnClickPendingIntent(R.id.next, getPendingIntent(context, R.id.next));
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



