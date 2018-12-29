package com.example.mk0730.alkolmetre.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mk0730.alkolmetre.data.FavoriteContract.FavoriteEntry;
import com.example.mk0730.alkolmetre.data.FavoriteContract.FavoriteDetailEntry;

public class FavoriteDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "alkolmetre.db";

    public FavoriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavoriteEntry.COLUMN_NAME + " TEXT, " +
                FavoriteEntry.COLUMN_API_ID + " TEXT NOT NULL, " +
                FavoriteEntry.COLUMN_IMAGE_URL + " TEXT, " +
                FavoriteEntry.COLUMN_ORIGIN + " TEXT " +
                " );";

        final String SQL_CREATE_FAVORITE_DETAILS_TABLE = "CREATE TABLE " + FavoriteDetailEntry.TABLE_NAME + " (" +
                FavoriteDetailEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavoriteDetailEntry.COLUMN_FAVORITE_ID + " TEXT NOT NULL, " +
                FavoriteDetailEntry.COLUMN_TASTING_NOTE + " TEXT, " +
                FavoriteDetailEntry.COLUMN_RELEASE_DATE + " INTEGER, " +
                FavoriteDetailEntry.COLUMN_SUGAR_CONTENT + " TEXT, " +
                FavoriteDetailEntry.COLUMN_ALCOHOL_CONTENT + " INTEGER " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteDetailEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
