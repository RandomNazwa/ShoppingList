package com.example.administrator.myapka;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ShopListView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
    private DBOpenHelperShops dbOpenHelperShops;

    private Shop shop;

    private TextView name;
    private TextView description;
    private TextView radius;
    private TextView latitude;
    private TextView longitude;


    public ShopListView(View view, Context context) {
        super(view);

        this.context = context;

        dbOpenHelperShops = new DBOpenHelperShops(context);

        name = view.findViewById(R.id.shopName);
        description = view.findViewById(R.id.description);
        radius = view.findViewById(R.id.radius);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);

        view.setOnClickListener(this);

      }


    public void bind(Shop shop) {
        this.shop = shop;
        name.setText(shop.name);
        description.setText(shop.description);
        radius.setText(shop.radius);
        latitude.setText(shop.latitude);
        longitude.setText(shop.longitude);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.context, ShopAddActivity.class);
        intent.putExtra(DBOpenHelperShops.COLUMN_NAME.id.toString(), this.shop.id);
        context.startActivity(intent);
    }
}
