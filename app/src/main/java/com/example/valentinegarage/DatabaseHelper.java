package com.example.valentinegarage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "valentines_db";
    public static final String TASK_TABLE_NAME = "task_assignment";
    public static final String STAFF_TABLE_NAME = "staff";
    public static final String ADMIN_TABLE_NAME = "admin";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    Boolean isAdmin = false;

    @Override
    public void onCreate(SQLiteDatabase db) {


        //Tasks table query
        String query = "CREATE TABLE " + TASK_TABLE_NAME + " (task_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "task_name VARCHAR(100)," +
                "staff_name VARCHAR(100)," +
                "task_status VARCHAR(100)," +
                "task_location VARCHAR(150),"+
                "start_date date," +
                "end_date date," +
                "car_name VARCHAR(200)," +
                "car_owner VARCHAR(200))";

        //Employee or staff query
        String create_staff = "CREATE TABLE " + STAFF_TABLE_NAME + "(staff_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "first_name VARCHAR(60)," +
                "last_name VARCHAR(60)," +
                "email VARCHAR(150)," +
                "user_type VARCHAR(60)," +
                "password VARCHAR(60)," +
                "UNIQUE(email) ON CONFLICT IGNORE" +
                ")";


        // Executing task query table
        db.execSQL(query);

        // Executing staff query
        db.execSQL(create_staff);
    }

    public void insert_admin(){
        SQLiteDatabase db = this.getWritableDatabase();

        String insert_admin = "INSERT INTO "+STAFF_TABLE_NAME+" (first_name,last_name,email,user_type,password)"+
                "VALUES ('Saima','Haitembu','saima@gmail.com','admin','12345')";

        db.execSQL(insert_admin);
    }

    // Insert staff function
    public boolean insert_staff(String first_name, String last_name, String email,String useType, String password ) {
        //Enabling the application to write to the database
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content value object
        ContentValues data = new ContentValues();

        // Adding to CV object
        data.put("first_name", first_name);
        data.put("last_name", last_name);
        data.put("email", email);
        data.put("user_type", useType);
        data.put("password", password);

        // inserting data into the table
        Long result = db.insert(STAFF_TABLE_NAME, null, data);

        // Checking if data was inserted and giving feedback
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Insert task function
    public boolean insert_task(String task_name, String staff_name, String task_status,String task_location, String start_date, String end_date, String car_name, String car_owner) {
        //Enabling the application to write to the database
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content value object
        ContentValues data = new ContentValues();

        // Adding to CV object
        data.put("task_name", task_name);
        data.put("staff_name", staff_name);
        data.put("task_status", task_status);
        data.put("task_location",task_location);
        data.put("start_date", start_date);
        data.put("end_date", end_date);
        data.put("car_name", car_name);
        data.put("car_owner", car_owner);

        // inserting data into the table
        Long result = db.insert(TASK_TABLE_NAME, null, data);

        // Checking if data was inserted and giving feedback
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Login function
    public Cursor login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // checking data from the database
        Cursor res = db.rawQuery("SELECT * FROM " + STAFF_TABLE_NAME + " WHERE email=? and password=?", new String[]{email, password});

        // returning the found results
        return res;
    }

    // fetching all tasks from the database
    public Cursor fetchTasks() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TASK_TABLE_NAME, null);

        return res;
    }

    // fetching all tasks from the database
    public Cursor fetchTasksName() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT task_name FROM " + TASK_TABLE_NAME, null);

        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STAFF_TABLE_NAME);

        onCreate(db);
    }
}
