package com.mertkavrayici.cartapp.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mertkavrayici.cartapp.models.Product;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shop_table";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";

    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRODUCT_TYPE = "product_type";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " INTEGER, " +

                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_PRODUCT_TYPE + " INTEGER)";
        db.execSQL(createTableQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addProduct( Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean exists = checkIfProductExists(product.getId());
        if(!exists){
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, product.getName());
            values.put(COLUMN_PRICE, product.getPrice());

            values.put(COLUMN_QUANTITY, product.getQuantity());
            values.put(COLUMN_PRODUCT_TYPE, product.getProductType());
            db.insert(TABLE_NAME, null, values);
        }

        db.close();


    }
    private byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    private Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    public void updateProductQuantity(int productId, int newQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int oldQuantity =getProductQuantity(productId);
        int quantity = oldQuantity-newQuantity;
        values.put(COLUMN_QUANTITY, quantity);
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(productId)});
        db.close();
    }

    public List<Product> getAllProducts(){
        List<Product> productList =new ArrayList<>();
        SQLiteDatabase db  =this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor != null && cursor.moveToFirst()) {

            do {

                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));


                @SuppressLint("Range") int quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
                @SuppressLint("Range") int productType = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_TYPE));


                Product product = new Product(id, name, price,null,quantity,productType);
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // Cursor kapatılır
        cursor.close();

        // Veritabanı bağlantısı kapatılır
        db.close();

        // Ürün listesi döndürülür
        return productList;
    }
    private boolean checkIfProductExists(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(productId) };
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
    @SuppressLint("Range")
    private int getProductQuantity(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_QUANTITY}, COLUMN_ID + " = ?",
                new String[]{String.valueOf(productId)}, null, null, null, null);
        int quantity = 0;
        if (cursor != null && cursor.moveToFirst()) {
            quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
            cursor.close();
        }

        return quantity;
    }

}
