package com.example.administrator.myapka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelperShops extends SQLiteOpenHelper{

    public enum COLUMN_NAME {id, name, description, radius, latitude, longitude}

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Shops.db";
    private static final String TABLE_NAME = "Shops";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME.id + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME.name + " TEXT," +
                    COLUMN_NAME.description + " TEXT," +
                    COLUMN_NAME.radius + " TEXT," +
                    COLUMN_NAME.latitude + " TEXT," +
                    COLUMN_NAME.longitude + " TEXT);";

    DBOpenHelperShops(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBOpenHelperShops.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//DODAWANIE SKLEPU------------------------------------------------------------------------------

    public void addShop(Shop shop) {

        ContentValues values = new ContentValues();
        values.put(String.valueOf(COLUMN_NAME.name), shop.getName());
        values.put(String.valueOf(COLUMN_NAME.description), shop.getDescription());
        values.put(String.valueOf(COLUMN_NAME.radius), shop.getRadius());
        values.put(String.valueOf(COLUMN_NAME.latitude), shop.getLatitude());
        values.put(String.valueOf(COLUMN_NAME.longitude), shop.getLongitude());


        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

//ZWRACANIE LISTY SKLEPÓW------------------------------------------------------------------------

    public List<Shop> getShops() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        List<Shop> shops = new ArrayList<>();
        while (cursor.moveToNext()) {
            shops.add(Pro(cursor));
        }
        cursor.close();

        return shops;
    }

    private static Shop Pro(Cursor cursor) {

        String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME.id.toString()));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME.name.toString()));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME.description.toString()));
        String radius = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME.radius.toString()));
        String latitude = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME.latitude.toString()));
        String longitude = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME.longitude.toString()));


        return new Shop(id, name,description, radius, latitude, longitude);
    }



}
