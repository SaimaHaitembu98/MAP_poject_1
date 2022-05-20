package com.example.valentinegarage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class HomePage extends AppCompatActivity {
    EditText task_name, staff_name, task_status,task_location, car_name, car_owner, start_date, end_date;
    TextView view_task,view_checklist,add_user;
    Button assign_btn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Getting text field id's
        view_task = (TextView) findViewById(R.id.view_task);
        view_checklist =(TextView) findViewById(R.id.view_checklist);
        add_user = (TextView) findViewById(R.id.add_staff_admin);

        // View task user click link
        view_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigating to the tasks page
                Intent i = new Intent(HomePage.this, Tasks.class);
                startActivity(i);
            }
        });

        // Checklist user click link
        view_checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,ListViewData.class);
                startActivity(i);
            }
        });

        // Add user click link
        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,Signup_page.class);
                startActivity(i);
            }
        });

        assign_task();
    }

    // Assign task function
    public void assign_task() {
        db = new DatabaseHelper(this);

        // Getting text field names
        task_name = (EditText) findViewById(R.id.task_name);
        staff_name = (EditText) findViewById(R.id.staff_name);
        task_status = (EditText) findViewById(R.id.task_status);
        task_location = (EditText) findViewById(R.id.task_location);
        start_date = (EditText) findViewById(R.id.start_date);
        end_date = (EditText) findViewById(R.id.end_date);
        car_name = (EditText) findViewById(R.id.car_name);
        car_owner = (EditText) findViewById(R.id.car_owner);
        assign_btn = (Button) findViewById(R.id.assign_btn);

        // Assign user button
        assign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Getting input fields data
                String task_n = task_name.getText().toString();
                String staff_n = staff_name.getText().toString();
                String task_st = task_status.getText().toString();
                String task_loc = task_location.getText().toString();
                String start_d = start_date.getText().toString();
                String end_d = end_date.getText().toString();
                String car_n = car_name.getText().toString();
                String car_ow = car_owner.getText().toString();

                if (TextUtils.isEmpty(task_n) || TextUtils.isEmpty(staff_n) || TextUtils.isEmpty(task_st)||TextUtils.isEmpty(task_loc) ||
                        TextUtils.isEmpty(start_d) || TextUtils.isEmpty(end_d) || TextUtils.isEmpty(car_n) ||
                        TextUtils.isEmpty(car_ow)) {
                    showMsg("Please provide the required information!");
                } else {
                    boolean is_inserted = db.insert_task(task_n, staff_n, task_st,task_loc, start_d, end_d, car_n, car_ow);

                    if (is_inserted) {
                        showMsg("Successfully assigned task to "+staff_n);

                        // Navigating to the tasks page
                        Intent i = new Intent(HomePage.this, Tasks.class);
                        startActivity(i);
                    } else {
                        showMsg("Error occurred while assigning task!");
                    }
                }
            }
        });
    }

    // Toast message display function
    public void showMsg(String msg) {
        Toast.makeText(HomePage.this, msg, Toast.LENGTH_LONG).show();
    }
}