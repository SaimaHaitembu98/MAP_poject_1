package com.example.valentinegarage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup_page extends AppCompatActivity {
    DatabaseHelper db;
    EditText first_name, last_name, email, userTYpe,password, c_password;
    Button submit_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        signup();
    }

    public void signup() {
        // User
        db = new DatabaseHelper(this);

        // Getting input fields ids
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        userTYpe=(EditText) findViewById(R.id.userType);
        password = (EditText) findViewById(R.id.password);
        c_password = (EditText) findViewById(R.id.c_password);
        submit_signup = (Button) findViewById(R.id.submit_signup);

        submit_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Checking if text fields are empty
                if (TextUtils.isEmpty(first_name.getText().toString()) || TextUtils.isEmpty(last_name.getText().toString())
                        || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())
                        || TextUtils.isEmpty(c_password.getText().toString()) || TextUtils.isEmpty(userTYpe.getText().toString())){
                    showMsg("Please provide the required information!");
                } else {
                    //Checking if passwords are matching
                    if (!password.getText().toString().equals(c_password.getText().toString())) {
                        showMsg("Password doesn't match!");
                    } else {
                        String first_n = first_name.getText().toString();
                        String last_n = last_name.getText().toString();
                        String mail = email.getText().toString();
                        String useType = userTYpe.getText().toString();
                        String pwd = password.getText().toString();

                        boolean is_inserted = db.insert_staff(first_n, last_n, mail,useType, pwd);

                        if (is_inserted) {
                            showMsg("Successfully Signed up");

                             Intent i = new Intent(getApplicationContext(), MainActivity.class);
                             startActivity(i);
                        } else {
                            showMsg("Error inserting data!");
                        }
                    }
                }
            }
        });


    }

    public void showMsg(String msg) {
        Toast.makeText(Signup_page.this, msg, Toast.LENGTH_SHORT).show();
    }

}