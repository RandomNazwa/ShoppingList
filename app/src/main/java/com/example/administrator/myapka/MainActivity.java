package com.example.administrator.myapka;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.myapka.OptionsActivity;
import com.example.administrator.myapka.ProductListActivity;
import com.example.administrator.myapka.R;

public class MainActivity extends AppCompatActivity {

    private NotificationActionReceiver notificationActionReceiver = new NotificationActionReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("MyActivity","Start app");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void click(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.shoppingListBtn:
                intent = new Intent(MainActivity.this, ProductListActivity.class);
                startActivity(intent);
                break;
            case R.id.optionsBtn:
                intent = new Intent(MainActivity.this, OptionsActivity.class);
                startActivity(intent);
                break;
            case R.id.shopListBtn:
                intent = new Intent(MainActivity.this, ShopListActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(notificationActionReceiver);
        Log.i("MainActivity", "notificationActionReceiver un-registered");

        super.onDestroy();
    }
}
