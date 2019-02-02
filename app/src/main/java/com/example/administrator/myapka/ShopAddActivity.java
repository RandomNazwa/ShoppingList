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

import com.example.administrator.myapka.ShopListActivity;
import com.example.administrator.myapka.R;

public class ShopAddActivity extends AppCompatActivity {

    private com.example.administrator.myapka.DBOpenHelperShops dbOpenHelperShops;
    private Context context;

    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbOpenHelperShops = new com.example.administrator.myapka.DBOpenHelperShops(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText name = findViewById(R.id.add_name);
        EditText description = findViewById(R.id.add_description);
        EditText radius = findViewById(R.id.add_radius);
        EditText latitude = findViewById(R.id.add_latitude);
        EditText longitude = findViewById(R.id.add_longitude);



        addBtn = findViewById(R.id.addBtn);

        Intent intent = getIntent();
        final int identify = intent.getIntExtra(com.example.administrator.myapka.DBOpenHelperShops.COLUMN_NAME.id.toString(), 0);
        com.example.administrator.myapka.Shop shop;



        addBtn.setOnClickListener(v -> {
            dbOpenHelperShops.addShop(new com.example.administrator.myapka.Shop(identify,
                    name.getText().toString(),description.getText().toString(),
                    radius.getText().toString(),latitude.getText().toString(),longitude.getText().toString()

            ));
            {
                startActivity(new Intent(this, ShopListActivity.class));

            }});


    }

}
