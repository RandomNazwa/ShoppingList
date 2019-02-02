package com.example.administrator.myapka;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListView> {

    private List<com.example.administrator.myapka.Product> products;
    private Context context;

    public ProductListAdapter(List<com.example.administrator.myapka.Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public ProductListView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.content_product_list_with_db, parent, false);

        return new ProductListView(view, this.context);
    }

    @Override
    public void onBindViewHolder(ProductListView viewHolder, int position) {
        viewHolder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }}

