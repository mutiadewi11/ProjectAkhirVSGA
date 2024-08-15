package com.example.projectakhirvsga.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.projectakhirvsga.database.DatabaseHelper;
import com.example.projectakhirvsga.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;

    public UserRepository(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
        if (database != null && database.isOpen()) {
            Log.d("Database Connected", "open: Successfully to connected database");
        } else {
            Toast.makeText(context, "Failed to connect to the database", Toast.LENGTH_SHORT).show();
        }
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
        dbHelper.close();
    }

    public long createUser(String name, String nim, String tempatLahir, String tanggalLahir, String alamat, String telepon, String tahunMasuk, String tahunLulus, String pekerjaan, String jabatan) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_NIM, nim);
        values.put(DatabaseHelper.COLUMN_TMPT, tempatLahir);
        values.put(DatabaseHelper.COLUMN_TGL, tanggalLahir);
        values.put(DatabaseHelper.COLUMN_DOMISILI, alamat);
        values.put(DatabaseHelper.COLUMN_TLP, telepon);
        values.put(DatabaseHelper.COLUMN_THNMSK, tahunMasuk);
        values.put(DatabaseHelper.COLUMN_THNLLS, tahunLulus);
        values.put(DatabaseHelper.COLUMN_JOB, pekerjaan);
        values.put(DatabaseHelper.COLUMN_JBTN, jabatan);

        return database.insert(DatabaseHelper.TABLE_USER, null, values);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = database.query(DatabaseHelper.TABLE_USER,
                    new String[]{
                            DatabaseHelper.COLUMN_ID,
                            DatabaseHelper.COLUMN_NAME,
                            DatabaseHelper.COLUMN_NIM,
                            DatabaseHelper.COLUMN_TMPT,
                            DatabaseHelper.COLUMN_TGL,
                            DatabaseHelper.COLUMN_DOMISILI,
                            DatabaseHelper.COLUMN_TLP,
                            DatabaseHelper.COLUMN_THNMSK,
                            DatabaseHelper.COLUMN_THNLLS,
                            DatabaseHelper.COLUMN_JOB,
                            DatabaseHelper.COLUMN_JBTN
                    },
                    null, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
                int nimIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NIM);
                int tmptIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TMPT);
                int tglIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TGL);
                int domisiliIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DOMISILI);
                int tlpIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TLP);
                int thnMskIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_THNMSK);
                int thnLlsIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_THNLLS);
                int jobIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_JOB);
                int jbtnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_JBTN);

                cursor.moveToFirst();
                do {
                    long id = cursor.getLong(idIndex);
                    String name = cursor.getString(nameIndex);
                    String nim = cursor.getString(nimIndex);
                    String tempatLahir = cursor.getString(tmptIndex);
                    String tanggalLahir = cursor.getString(tglIndex);
                    String alamat = cursor.getString(domisiliIndex);
                    String telepon = cursor.getString(tlpIndex);
                    String tahunMasuk = cursor.getString(thnMskIndex);
                    String tahunLulus = cursor.getString(thnLlsIndex);
                    String pekerjaan = cursor.getString(jobIndex);
                    String jabatan = cursor.getString(jbtnIndex);

                    users.add(new User(id, name, nim, tempatLahir, tanggalLahir, alamat, telepon, tahunMasuk, tahunLulus, pekerjaan, jabatan));
                } while (cursor.moveToNext());
            } else {
                Log.e("UserRepository", "Cursor is null or empty");
            }
        } catch (Exception e) {
            Log.e("UserRepository", "Error while fetching users: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return users;
    }

    public User getUserById(long id) {
        User user = null;
        Cursor cursor = null;
        try {
            cursor = database.query(DatabaseHelper.TABLE_USER,
                    new String[]{DatabaseHelper.COLUMN_ID,
                            DatabaseHelper.COLUMN_NAME,
                            DatabaseHelper.COLUMN_NIM,
                            DatabaseHelper.COLUMN_TMPT,
                            DatabaseHelper.COLUMN_TGL,
                            DatabaseHelper.COLUMN_DOMISILI,
                            DatabaseHelper.COLUMN_TLP,
                            DatabaseHelper.COLUMN_THNMSK,
                            DatabaseHelper.COLUMN_THNLLS,
                            DatabaseHelper.COLUMN_JOB,
                            DatabaseHelper.COLUMN_JBTN},
                    DatabaseHelper.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
                int nimIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NIM);
                int tmptIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TMPT);
                int tglIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TGL);
                int domisiliIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DOMISILI);
                int tlpIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TLP);
                int thnMskIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_THNMSK);
                int thnLlsIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_THNLLS);
                int jobIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_JOB);
                int jbtnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_JBTN);

                if (idIndex != -1 && nameIndex != -1 && domisiliIndex != -1) {
                    long userId = cursor.getLong(idIndex);
                    String name = cursor.getString(nameIndex);
                    String nim = cursor.getString(nimIndex);
                    String tempatLahir = cursor.getString(tmptIndex);
                    String tanggalLahir = cursor.getString(tglIndex);
                    String alamat = cursor.getString(domisiliIndex);
                    String telepon = cursor.getString(tlpIndex);
                    String tahunMasuk = cursor.getString(thnMskIndex);
                    String tahunLulus = cursor.getString(thnLlsIndex);
                    String pekerjaan = cursor.getString(jobIndex);
                    String jabatan = cursor.getString(jbtnIndex);
                    user = new User(userId, name, nim, tempatLahir, tanggalLahir, alamat, tahunMasuk, tahunLulus, pekerjaan, telepon, jabatan);
                } else {
                    Log.e("UserRepository", "One or more columns are missing in the cursor");
                }
            } else {
                Log.e("UserRepository", "Cursor is null or empty");
            }
        } catch (Exception e) {
            Log.e("UserRepository", "Error while fetching user by ID: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return user;
    }

    public int updateUser(long id, String name, String nim, String tempatLahir, String tanggalLahir, String alamat, String telepon, String tahunMasuk, String tahunLulus, String pekerjaan, String jabatan, String tlp) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_NIM, nim);
        values.put(DatabaseHelper.COLUMN_TMPT, tempatLahir);
        values.put(DatabaseHelper.COLUMN_TGL, tanggalLahir);
        values.put(DatabaseHelper.COLUMN_DOMISILI, alamat);
        values.put(DatabaseHelper.COLUMN_TLP, telepon);
        values.put(DatabaseHelper.COLUMN_THNMSK, tahunMasuk);
        values.put(DatabaseHelper.COLUMN_THNLLS, tahunLulus);
        values.put(DatabaseHelper.COLUMN_JOB, pekerjaan);
        values.put(DatabaseHelper.COLUMN_JBTN, jabatan);

        return database.update(DatabaseHelper.TABLE_USER, values,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUser(long id) {
        database.delete(DatabaseHelper.TABLE_USER,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
