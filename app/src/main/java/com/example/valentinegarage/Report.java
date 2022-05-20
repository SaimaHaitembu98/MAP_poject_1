package com.example.valentinegarage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class Report extends AppCompatActivity {
    DatabaseHelper db;
    private static final String FILE_NAME = "Valentine.txt";
    Button report_btn;
    Date date;
    DateFormat dateFormat;
    TextView task_name, task_view, staff_name, staff_view, task_status, status_view, start_date, start_view, end_date, end_view, car_name, name_view, car_owner, owner_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        // Calling the display data function
        displayDats();
    }

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

                    start_date = new TextView(this);
                    start_view = new TextView(this);
                    start_view.setText("Start date");
                    start_view.setTypeface(null, Typeface.BOLD);
                    start_view.setTextColor(resources.getColor(R.color.view_color));
                    start_view.setTextSize(16);
                    start_date.setText(res.getString(4));
                    layout.addView(start_view);
                    layout.addView(start_date);

                    end_view = new TextView(this);
                    end_date = new TextView(this);
                    end_view.setText("End date");
                    end_view.setTypeface(null, Typeface.BOLD);
                    end_view.setTextColor(resources.getColor(R.color.view_color));
                    end_view.setTextSize(16);
                    end_date.setText(res.getString(5));
                    layout.addView(end_view);
                    layout.addView(end_date);

                    car_name = new TextView(this);
                    name_view = new TextView(this);
                    name_view.setText("Car name and model");
                    name_view.setTypeface(null, Typeface.BOLD);
                    name_view.setTextColor(resources.getColor(R.color.view_color));
                    name_view.setTextSize(16);
                    car_name.setText(res.getString(6));
                    layout.addView(name_view);
                    layout.addView(car_name);

                    car_owner = new TextView(this);
                    owner_view = new TextView(this);
                    owner_view.setText("Car owner");
                    owner_view.setTypeface(null, Typeface.BOLD);
                    owner_view.setTextColor(resources.getColor(R.color.view_color));
                    owner_view.setTextSize(16);
                    car_owner.setText(res.getString(7));
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

//    public void generateReport() {
//        report_btn = (Button) findViewById(R.id.generate_report);
//
//        db = new DatabaseHelper(this);
//
//        report_btn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                FileOutputStream fos = null;
//
//
//                try {
//                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
//                    Cursor res = db.fetchTasks();
//
//                    if (res.getCount() != 0)
//                        for (int i = 0; i < res.getCount(); i++) {
//                            while (res.moveToNext()) {
//                                fos.write(res.getString(0).getBytes());
//                                fos.write(res.getString(1).getBytes());
//                                fos.write(res.getString(2).getBytes());
//                                fos.write(res.getString(3).getBytes());
//                                fos.write(res.getString(4).getBytes());
//                                fos.write(res.getString(5).getBytes());
//                                fos.write(res.getString(6).getBytes());
//                                fos.write(res.getString(7).getBytes());
//                                fos.write(res.getString(8).getBytes());
//
//
//
//                            }
//                        }
//                    Toast.makeText(Report.this, "Report saved to: " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (fos != null) {
//                        try {
//                            fos.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//
//        });
//
//    }
}