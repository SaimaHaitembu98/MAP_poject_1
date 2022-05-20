package com.example.valentinegarage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tasks extends AppCompatActivity {
    DatabaseHelper db;
    private static final String FILE_NAME = "Valentine.txt";
    Button report_btn, view_task_back_btn;
    Date date;
    DateFormat dateFormat;
    TextView task_name, task_view, staff_name, staff_view, task_status, status_view, task_location, location_view, start_date, start_view, end_date, end_view, car_name, name_view, car_owner, owner_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        // Calling the display data function
        displayDats();
    }

    // Display task data function
    public void displayDats() {
        db = new DatabaseHelper(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.l_layout);
        Resources resources = this.getResources();

        Cursor res = db.fetchTasks();
        if (res.getCount() != 0) {
            for (int i = 0; i < res.getCount(); i++) {
                while (res.moveToNext()) {
                    task_name = new TextView(this);
                    task_view = new TextView(this);
                    task_view.setText("Task name");
                    task_view.setTypeface(null, Typeface.BOLD);
                    task_view.setTextColor(resources.getColor(R.color.view_color));
                    task_view.setTextSize(16);
                    task_name.setText(res.getString(1));
                    layout.addView(task_view);
                    layout.addView(task_name);

                    staff_view = new TextView(this);
                    staff_name = new TextView(this);
                    staff_view.setText("Staff name");
                    staff_view.setTypeface(null, Typeface.BOLD);
                    staff_view.setTextColor(resources.getColor(R.color.view_color));
                    staff_view.setTextSize(16);
                    staff_name.setText(res.getString(2));
                    layout.addView(staff_view);
                    layout.addView(staff_name);

                    status_view = new TextView(this);
                    task_status = new TextView(this);
                    status_view.setText("Task status");
                    status_view.setTypeface(null, Typeface.BOLD);
                    status_view.setTextColor(resources.getColor(R.color.view_color));
                    status_view.setTextSize(16);
                    task_status.setText(res.getString(3));
                    layout.addView(status_view);
                    layout.addView(task_status);

                    location_view = new TextView(this);
                    task_location = new TextView(this);
                    location_view.setText("Task status");
                    location_view.setTypeface(null, Typeface.BOLD);
                    location_view.setTextColor(resources.getColor(R.color.view_color));
                    location_view.setTextSize(16);
                    task_location.setText(res.getString(4));
                    layout.addView(location_view);
                    layout.addView(task_location);

                    start_date = new TextView(this);
                    start_view = new TextView(this);
                    start_view.setText("Start date");
                    start_view.setTypeface(null, Typeface.BOLD);
                    start_view.setTextColor(resources.getColor(R.color.view_color));
                    start_view.setTextSize(16);
                    start_date.setText(res.getString(5));
                    layout.addView(start_view);
                    layout.addView(start_date);

                    end_view = new TextView(this);
                    end_date = new TextView(this);
                    end_view.setText("End date");
                    end_view.setTypeface(null, Typeface.BOLD);
                    end_view.setTextColor(resources.getColor(R.color.view_color));
                    end_view.setTextSize(16);
                    end_date.setText(res.getString(6));
                    layout.addView(end_view);
                    layout.addView(end_date);

                    car_name = new TextView(this);
                    name_view = new TextView(this);
                    name_view.setText("Car name and model");
                    name_view.setTypeface(null, Typeface.BOLD);
                    name_view.setTextColor(resources.getColor(R.color.view_color));
                    name_view.setTextSize(16);
                    car_name.setText(res.getString(7));
                    layout.addView(name_view);
                    layout.addView(car_name);

                    car_owner = new TextView(this);
                    owner_view = new TextView(this);
                    owner_view.setText("Car owner");
                    owner_view.setTypeface(null, Typeface.BOLD);
                    owner_view.setTextColor(resources.getColor(R.color.view_color));
                    owner_view.setTextSize(16);
                    car_owner.setText(res.getString(8));
                    car_owner.setPadding(0, 0, 0, 35);
                    layout.addView(owner_view);
                    layout.addView(car_owner);

                    TextView divider = new TextView(this);
                    divider.setBackgroundColor(resources.getColor(R.color.gray));
                    divider.setHeight(1);
                    layout.addView(divider);

                }
            }
        } else {
            showMessage("No tasks", "No tasks was found!");
        }
        res.close();
    }

    //Show message function
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}