package com.kamiyuri.studentmanagement.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.github.javafaker.Faker;
import com.kamiyuri.studentmanagement.Model.Student;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLite {
    SQLiteDatabase database;
    private static SQLite instance;

    public SQLite(Context context) {
        try {
            database = SQLiteDatabase.openDatabase(context.getApplicationContext().getFilesDir() + "/data", null, SQLiteDatabase.CREATE_IF_NECESSARY);
            init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static SQLite getInstance(Context context) {
        if (instance == null) {
            instance = new SQLite(context);
        }
        return instance;
    }

    public void init() {
        database.beginTransaction();
        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS student(id integer PRIMARY KEY AUTOINCREMENT, mssv integer ,name text, email text, birthday date)");
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }

        int i = 100;
        while (i-- >= 0) {
            database.beginTransaction();
            try {
                ContentValues cv = new ContentValues();

                cv.put("mssv", Faker.instance().number().randomNumber(4, true));
                cv.put("name", Faker.instance().name().name());
                cv.put("email", Faker.instance().internet().emailAddress());
                cv.put("birthday", String.valueOf(new Timestamp(Faker.instance().date().birthday(18, 23).getTime())));

                database.insert("student", null, cv);

                database.setTransactionSuccessful();

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                database.endTransaction();
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    public ArrayList<Student> getStudents() throws ParseException {
        ArrayList<Student> res = new ArrayList<>();

        String[] columns = {"mssv", "name", "email", "birthday"};
        @SuppressLint("Recycle") Cursor cs = database.query("student", columns,
                null, null, null, null ,null);

        Log.v("TAG", "# records: " + cs.getCount());

        cs.moveToPosition(-1);
        while (cs.moveToNext()) {
            String mssv = cs.getString(0);
            String name = cs.getString(1);
            String email = cs.getString(2);
            String birthday = cs.getString(3);

            res.add(new Student(mssv, name, email, birthday));
        }
        return res;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}
