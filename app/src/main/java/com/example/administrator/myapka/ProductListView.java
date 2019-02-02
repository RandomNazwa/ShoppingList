package com.example.administrator.myapka;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ProductListView extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static DecimalFormat formatPrice = new DecimalFormat("$#.00");

    private Context context;
    private DBOpenHelper dbOpenHelper;

    private Product product;

    private TextView name;
    private TextView price;
    private TextView quantity;
    private CheckBox bought;

    public ProductListView(View view, Context context) {
        super(view);

        this.context = context;

        dbOpenHelper = new DBOpenHelper(context);

        name = view.findViewById(R.id.productName);
        price = view.findViewById(R.id.productPrice);
        quantity = view.findViewById(R.id.productQuantity);
        bought = view.findViewById(R.id.product_bought);

        view.setOnClickListener(this);

        bought.setOnClickListener(v -> {
            product.bought = !product.bought;
            dbOpenHelper.addProduct(product);

        });
    }


    public void bind(Product product) {
        this.product = product;
        Log.d("MyActivity","Product:"+product);
        name.setText(product.name);
        Log.d("MyActivity", "Product's name: "+product.name);
        price.setText(formatPrice.format(product.price));
        quantity.setText(Integer.toString(product.quantity));
        bought.setChecked(product.bought);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.context, ProductAddActivity.class);
        intent.putExtra(DBOpenHelper.COLUMN_NAME.id.toString(), this.product.id);
        context.startActivity(intent);
    }
}


