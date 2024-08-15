package com.example.projectakhirvsga.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names for User
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_TMPT = "tempat_lahir";
    public static final String COLUMN_TGL = "tanggal_lahir";
    public static final String COLUMN_DOMISILI = "alamat";
    public static final String COLUMN_TLP = "telepon";
    public static final String COLUMN_THNMSK = "tahun_masuk";
    public static final String COLUMN_THNLLS = "tahun_lulus";
    public static final String COLUMN_JOB = "pekerjaan";
    public static final String COLUMN_JBTN = "jabatan";

    // Table name and column names for News
    public static final String TABLE_NEWS = "news";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_PATH_IMAGE = "path_image";

    // SQL statement to create the User table
    private static final String TABLE_CREATE_USER =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL);",
                    TABLE_USER,
                    COLUMN_ID,
                    COLUMN_NAME,
                    COLUMN_NIM,
                    COLUMN_TMPT,
                    COLUMN_TGL,
                    COLUMN_DOMISILI,
                    COLUMN_TLP,
                    COLUMN_THNMSK,
                    COLUMN_THNLLS,
                    COLUMN_JOB,
                    COLUMN_JBTN);

    // SQL statement to create the News table
    private static final String TABLE_CREATE_NEWS =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT);",
                    TABLE_NEWS,
                    COLUMN_ID,
                    COLUMN_TITLE,
                    COLUMN_CONTENT,
                    COLUMN_PATH_IMAGE);

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the SQL statement to create the tables
        db.execSQL(TABLE_CREATE_USER);
        db.execSQL(TABLE_CREATE_NEWS);
        insertDummyData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old tables and create new ones
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
        onCreate(db);
    }

    private void insertDummyData(SQLiteDatabase db) {
        List<String> titles = Arrays.asList(
                "TECH WEEKLY: How Mercedes finally unlocked the secrets of the ground effect regulations",
                "EXCLUSIVE: ’You have to take risk everywhere’ – Fred Vasseur on how he’s going to take Ferrari back to the top",
                "Red Bull make decision on Perez's future and RB driver line-up"
        );

        List<String> contents = Arrays.asList(
                "The Mercedes W15 that began this season did not look so outwardly different to the car which won three of the last four races before the summer break...",
                "Twenty months into the biggest job in Formula 1 and Ferrari boss Fred Vasseur is methodically chipping away at reshaping the world’s most famous racing team...",
                "Red Bull are sticking with Sergio Perez and will field unchanged line-ups in their works team and sister squad RB when the Formula 1 season resumes..."
        );

        List<String> imageResourceIds = Arrays.asList(
                "drawable/w15",
                "drawable/ferrari",
                "drawable/redbull"
        );

        for (int i = 0; i < titles.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, titles.get(i));
            values.put(COLUMN_CONTENT, contents.get(i));
            values.put(COLUMN_PATH_IMAGE, imageResourceIds.get(i));

            // Log values for debugging
            Log.d("DatabaseHelper", "Inserting data: " + values);

            long rowId = db.insert(TABLE_NEWS, null, values);
            if (rowId == -1L) {
                Log.e("DatabaseHelper", "Failed to insert row for title: " + titles.get(i));
            } else {
                Log.d("DatabaseHelper", "Inserted row with ID: " + rowId);
            }
        }
    }
}
