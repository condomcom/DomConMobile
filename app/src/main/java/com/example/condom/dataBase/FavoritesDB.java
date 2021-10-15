package com.example.condom.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.String;

public class FavoritesDB extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "FavoriteCards";
    private static final String DATABASE_NAME = "PerformancesCardsDB";
    public static String KEY_ID = "id";
    public static String ITEM_TITLE ="itemTitle";
    public static String ITEM_IMAGE = "itemImage";
    public static String ITEM_DESCRIPTION = "itemDescription";
    public static String ITEM_BEGINNING = "itemBeginning";
    public static String ITEM_DURATION = "itemDuration";
    public static String FAVORITE_STATUS = "favStatus";
    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +"("
            + KEY_ID + " TEXT," + ITEM_TITLE + " TEXT," + ITEM_IMAGE
            + " TEXT," + ITEM_DESCRIPTION + " TEXT," + ITEM_BEGINNING
            + " TEXT," + ITEM_DURATION + " TEXT," + FAVORITE_STATUS
            + " TEXT)";
    private static final String TAG = "RecordsDbHelper";

    public FavoritesDB(Context context){super(context, DATABASE_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS tasks");
        onCreate(db);
    }

    //создать пустую таблицу
    public void insertEmpty(){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(int x = 1; x < 5; x++){
            contentValues.put(KEY_ID, x);
            contentValues.put(FAVORITE_STATUS, "0");

            database.insert(TABLE_NAME, null, contentValues);
        }
    }

    //вставить данные в бд
    public void insertIntoDatabase(String item_title, int item_image, String item_description,
                                   String item_beginning, String item_duration, String fav_status, String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ITEM_TITLE, item_title);
        contentValues.put(ITEM_IMAGE, item_image);
        contentValues.put(KEY_ID, id);
        contentValues.put(ITEM_DESCRIPTION, item_description);
        contentValues.put(ITEM_BEGINNING, item_beginning);
        contentValues.put(ITEM_DURATION, item_duration);
        contentValues.put(FAVORITE_STATUS, fav_status);

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        Log.d("FavoritesDB status", item_title + ", favStatus - " + fav_status
        + "-, " + contentValues);
    }

    //считывание данных
    public Cursor readData(String id){
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID + "=" + id + "";

        return database.rawQuery(sql, null, null);
    }

    //удаление строки из бд
    public void removeFav(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET " + FAVORITE_STATUS + " ='0' WHERE " + KEY_ID
                + "=" +id + "";
        sqLiteDatabase.execSQL(sql);
        Log.d("remove", id.toString());
    }

    public Cursor selectAllFavoriteList(){
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + FAVORITE_STATUS + " ='1'";

        return database.rawQuery(sql, null, null);
    }
}
