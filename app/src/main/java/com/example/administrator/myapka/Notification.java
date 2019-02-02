package com.example.administrator.myapka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class Notification extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(context.getResources().getString(R.string.action_product_list))) {
            context.startActivity(new Intent(context, ProductListActivity.class));
        }

        if (intent.getAction().equals(context.getResources().getString(R.string.action_product_edit))) {
            Intent editIntent = new Intent(context, ProductAddActivity.class);
            editIntent.putExtra(DBOpenHelper.COLUMN_NAME.id.toString(),
                    intent.getIntExtra(DBOpenHelper.COLUMN_NAME.id.toString(), 0));
            context.startActivity(editIntent);
        }
    }
}

