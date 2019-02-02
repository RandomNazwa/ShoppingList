package com.example.administrator.myapka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapka.ProductListActivity;
import com.example.administrator.myapka.R;

public class ProductAddActivity extends AppCompatActivity {

    private com.example.administrator.myapka.DBOpenHelper dbOpenHelper;
    private Context context;

    private Button deleteBtn;
    private Button addBtn;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MyActivity", "init aktywności");

        dbOpenHelper = new com.example.administrator.myapka.DBOpenHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText name = findViewById(R.id.add_name);
        EditText price = findViewById(R.id.add_price);
        EditText quantity = findViewById(R.id.add_quantity);
        CheckBox bought = findViewById(R.id.edit_bought);


        deleteBtn = findViewById(R.id.deleteBtn);
        addBtn = findViewById(R.id.addBtn);
        saveBtn = findViewById(R.id.saveBtn);

        Intent intent = getIntent();
        final int identify = intent.getIntExtra(com.example.administrator.myapka.DBOpenHelper.COLUMN_NAME.id.toString(), 0);
        com.example.administrator.myapka.Product product;

        //produkt istnieje
        if (identify != 0) {
            product = dbOpenHelper.getOneProduct(identify);
            name.setText(product.name);
            price.setText(Double.toString(product.price));
            quantity.setText(Integer.toString(product.quantity));
            if (product.bought) bought.setChecked(true);

            addBtn.setVisibility(View.GONE);

            saveBtn.setOnClickListener(v -> {
                product.name = name.getText().toString();
                product.price = Double.parseDouble(price.getText().toString());
                product.quantity = Integer.parseInt(quantity.getText().toString());
                product.bought = bought.isChecked();
                dbOpenHelper.updateProduct(identify, product);

                startActivity(new Intent(this, ProductListActivity.class));
            });

//            deleteBtn.setVisibility(View.);
            deleteBtn.setOnClickListener(v -> {
                dbOpenHelper.deleteProduct(identify);
                startActivity(new Intent(this, ProductListActivity.class));
            });
        }
        //produkt nie istnieje
        else {
            saveBtn.setVisibility(View.GONE);

            addBtn.setOnClickListener(v -> {
                dbOpenHelper.addProduct(new com.example.administrator.myapka.Product(identify,
                        name.getText().toString(),
                        Double.parseDouble(price.getText().toString()),
                        Integer.parseInt(quantity.getText().toString()),
                        bought.isChecked()
                ));
                { Toast.makeText(context, name.getText() + ", price: " + price.getText() + ", quantity: " + quantity.getText() + "  added!", Toast.LENGTH_LONG)
                        .show();
                startActivity(new Intent(this, ProductListActivity.class));

            }});
            deleteBtn.setVisibility(View.GONE);
        }


    }
}
