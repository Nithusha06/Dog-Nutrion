package com.example.nutrionapp;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NutrionApp_db";
    private static final int DATABASE_VERSION = 1;

    // Product table and columns
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_IMAGE_URI = "image_uri";

    // Cart table and columns
    public static final String TABLE_CART = "cart";
    public static final String CART_COLUMN_ID = "id";
    public static final String CART_COLUMN_PRODUCT_NAME = "product_name";
    public static final String CART_COLUMN_PRICE = "price";
    public static final String CART_COLUMN_IMAGE_URI = "image_uri";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the products table
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRICE + " REAL, " +
                COLUMN_RATING + " REAL, " +
                COLUMN_IMAGE_URI + " TEXT)";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        // Create the cart table
        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + " (" +
                CART_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CART_COLUMN_PRODUCT_NAME + " TEXT, " +
                CART_COLUMN_PRICE + " REAL, " +
                CART_COLUMN_IMAGE_URI + " TEXT)";
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        // Create tables again
        onCreate(db);
    }

    // Insert product into the database
    public long insertProduct(String name, String description, double price, double rating, String imageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_RATING, rating);
        values.put(COLUMN_IMAGE_URI, imageUri);

        // Insert the data and return the row ID
        return db.insert(TABLE_PRODUCTS, null, values);
    }

    // Get all products from the database
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get all products
        String SELECT_QUERY = "SELECT * FROM " + TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);

        // Loop through the cursor and retrieve the products
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Ensure column index is valid
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
                int priceIndex = cursor.getColumnIndex(COLUMN_PRICE);
                int ratingIndex = cursor.getColumnIndex(COLUMN_RATING);
                int imageUriIndex = cursor.getColumnIndex(COLUMN_IMAGE_URI);

                // Check if column index is valid (>=0)
                if (idIndex >= 0 && nameIndex >= 0 && descriptionIndex >= 0 &&
                    priceIndex >= 0 && ratingIndex >= 0 && imageUriIndex >= 0) {

                    // Retrieve the data from the cursor
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String description = cursor.getString(descriptionIndex);
                    double price = cursor.getDouble(priceIndex);
                    double rating = cursor.getDouble(ratingIndex);
                    String imageUri = cursor.getString(imageUriIndex);

                    // Add product to the list
                    productList.add(new Product(id, name, description, price, rating, imageUri));
                } else {
                    // Handle case where column is not found (index < 0)
                    // For now, just log an error message or take necessary action
                    // Log.e("Database", "Column not found!");
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return productList;
    }

    // Method to insert items into the cart
    public long addToCart(String productName, double price, String imageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CART_COLUMN_PRODUCT_NAME, productName);
        values.put(CART_COLUMN_PRICE, price);
        values.put(CART_COLUMN_IMAGE_URI, imageUri);

        return db.insert(TABLE_CART, null, values);
    }

    // Method to retrieve all cart items
    public List<ProductItem> getCartItems() {
        List<ProductItem> cartList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String SELECT_QUERY = "SELECT * FROM " + TABLE_CART;
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(CART_COLUMN_PRODUCT_NAME));
                double price = cursor.getDouble(cursor.getColumnIndex(CART_COLUMN_PRICE));
                String imageUri = cursor.getString(cursor.getColumnIndex(CART_COLUMN_IMAGE_URI));

                cartList.add(new ProductItem(name, "", "", "", "$" + price, R.drawable.prod1)); // Use imageUri for real image loading
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return cartList;
    }
}
