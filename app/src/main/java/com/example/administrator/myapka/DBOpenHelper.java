package com.example.administrator.myapka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper {

    public enum COLUMN_NAME {id, name, price, quantity, bought}

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Products.db";
    private static final String TABLE_NAME = "Products";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME.id + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME.name + " TEXT," +
                    COLUMN_NAME.price + " REAL," +
                    COLUMN_NAME.quantity + " INTEGER," +
                    COLUMN_NAME.bought + " NUMERIC);";

    DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//DODAWANIE PRODUKTU------------------------------------------------------------------------------

    public void addProduct(Product product) {

        ContentValues values = new ContentValues();
        values.put(String.valueOf(COLUMN_NAME.name), product.getName());
        values.put(String.valueOf(COLUMN_NAME.price), product.getPrice());
        values.put(String.valueOf(COLUMN_NAME.quantity), product.getQuantity());
        values.put(String.valueOf(COLUMN_NAME.bought), product.isBought());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Aktualizacja

    public void updateProduct(int identify, Product product) {

        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME.id + " =  \"" + identify + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
//            product.setId(Integer.parseInt(cursor.getString(0)));
            ContentValues values = new ContentValues();
            values.put(String.valueOf(COLUMN_NAME.name), product.getName());
            values.put(String.valueOf(COLUMN_NAME.price), product.getPrice());
            Log.d("MyActivity","Updating price with:"+product.getPrice());
            values.put(String.valueOf(COLUMN_NAME.quantity), product.getQuantity());
            values.put(String.valueOf(COLUMN_NAME.bought), product.isBought());
            db.update(TABLE_NAME, values, COLUMN_NAME.id + " = ?", new String[]{String.valueOf(product.getId())});
            cursor.close();
            result = true;
        }
    }
//ZWRACANIE LISTY PRODUKTÓW------------------------------------------------------------------------

    public List<Product> getProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        List<Product> products = new ArrayList<>();
        while (cursor.moveToNext()) {
            products.add(Pro(cursor));
        }
        cursor.close();

        return products;
    }

    private static Product Pro(Cursor cursor) {

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME.id.toString()));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME.name.toString()));
        double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NAME.price.toString()));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME.quantity.toString()));
        boolean bought = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME.bought.toString())) == 1;

        return new Product(id, name, price, quantity, bought);
    }

    public Product getOneProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_NAME.id + " = ?",
                new String[]{Integer.toString(id)}, null, null, null);
        cursor.moveToNext();
        return Pro(cursor);
    }
//USUWANIE PRODUKTU---------------------------------------------------------------------------

    public void deleteProduct(int identify) {

        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME.id + " =  \"" + identify + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Product product = new Product();
        if (cursor.moveToFirst()) {
            product.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_NAME.id + " = ?",
                    new String[]{String.valueOf(product.getId())});
            cursor.close();
            result = true;
        }
    }

}
