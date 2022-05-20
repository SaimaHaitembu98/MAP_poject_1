package com.example.valentinegarage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView email, password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        // Getting text field variable id's
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);
        db.insert_admin();

        // Getting data after submission button clicked
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pwd = password.getText().toString();

                Cursor res = db.login(mail, pwd);

                if (res.getCount() == 0) {
                    showMsg("Invalid username or password");
                } else {


                    String user_type = "";

                    if (res.getCount() != 0) {
                        for (int i = 0; i < res.getCount(); i++) {
                            while (res.moveToNext()) {
                                user_type = res.getString(4);
                            }
                        }
                    }

                    showMsg("Successfully logged in: "+user_type);

                    if (user_type.equalsIgnoreCase("admin")) {
                        Intent i = new Intent(MainActivity.this, HomePage.class);
                        startActivity(i);
                    } else if (user_type.equalsIgnoreCase("staff")) {
                        Intent i = new Intent(MainActivity.this, ListViewData.class);
                        startActivity(i);
                    }
                }
            }
        });
    }


    public void showMsg(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}



