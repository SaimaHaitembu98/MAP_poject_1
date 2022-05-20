package com.example.valentinegarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewData extends AppCompatActivity {

    ListView listViewData;
//    Button checklist_back_btn;
    ArrayAdapter<String> adapter;
    String[] task = {"Painting", "Replacing tyres", "Engine starters", "Lights", "Trailer"};
    ArrayList<String> name = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_data);

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.fetchTasksName();
        res.moveToNext();

        while (!res.isAfterLast()) {
            name.add(res.getString(0));
            res.moveToNext();
        }

        res.close();

        listViewData = findViewById(R.id.listView_data);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, name);
        listViewData.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item_done) {
            String itemSelected = "Selected item: \n";
            for (int i = 0; i < listViewData.getCount(); i++) {
                if (listViewData.isItemChecked(i)) {
                    itemSelected += listViewData.getItemAtPosition(i) + "\n";
                }

                Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}