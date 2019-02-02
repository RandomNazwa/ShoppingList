package com.example.administrator.myapka;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.administrator.myapka.MusicReceiver;
import com.example.administrator.myapka.Music;
import com.example.administrator.myapka.R;


public class MyWidget extends AppWidgetProvider {

    private static final int[] IMAGES={R.drawable.naruto,R.drawable.jiraiya,R.drawable.itachi};

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        setGoToWebButton(context, views);
        setMusicButtons(context, views);
        Music.Initialize();
        setImageView(context, views);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static void setGoToWebButton(Context context, RemoteViews views) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("http://www.pja.edu.pl"));

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.text, pendingIntent);
    }

    private static void setMusicButtons(Context context, RemoteViews views) {
        Intent stopIntent = new Intent(context, MusicReceiver.class);
        Intent playIntent = new Intent(context, MusicReceiver.class);
        Intent nextIntent = new Intent(context, MusicReceiver.class);

        stopIntent.setAction("stop");
        playIntent.setAction("play");
        nextIntent.setAction("next");

        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(context, 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(context, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.widget_stop, stopPendingIntent);
        views.setOnClickPendingIntent(R.id.widget_play, playPendingIntent);
        views.setOnClickPendingIntent(R.id.widget_next, nextPendingIntent);

    }

    private static void setImageView(Context context, RemoteViews views){

        views.setImageViewResource(R.id.imageView,R.drawable.itachi);

        Intent intent = new Intent(context,MyWidget.class);
        intent.setAction("intent");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.random_image, pendingIntent);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);

        if (intent.getAction().equals("intent")) {
            views.setImageViewResource(R.id.imageView,
                    IMAGES[(int) (Math.random() * 3)]);
            appWidgetManager.updateAppWidget(new ComponentName(context, MyWidget.class), views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}
