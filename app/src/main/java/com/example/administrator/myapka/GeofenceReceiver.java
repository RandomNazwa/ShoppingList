package com.example.administrator.myapka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import static com.example.administrator.myapka.GeofenceTransitionsIntentService.enqueueWork;

public class GeofenceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        enqueueWork(context,intent);
    }
}
