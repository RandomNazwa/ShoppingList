package com.example.administrator.myapka;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ShopListAdapter extends  RecyclerView.Adapter<ShopListView>  {

    private List<Shop> shops;
    private Context context;

    public ShopListAdapter(List<com.example.administrator.myapka.Shop> shops, Context context) {
        this.shops = shops;
        this.context = context;
    }

    @Override
    public ShopListView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.content_shop_list_with_db, parent, false);

        return new ShopListView(view, this.context);
    }

    @Override
    public void onBindViewHolder(ShopListView viewHolder, int position) {
        viewHolder.bind(shops.get(position));
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }}