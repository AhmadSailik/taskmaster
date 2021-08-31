package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Task>allTask=new ArrayList<Task>();
        allTask.add(new Task("jop","still research","in progress"));
        allTask.add(new Task("lab","add All task","complete"));
        allTask.add(new Task("CSS","course CSS","assigned"));
        RecyclerView allTaskRecyclerView=findViewById(R.id.listOfTask);
        allTaskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        allTaskRecyclerView.setAdapter(new vickAdapter(allTask));
        allTaskRecyclerView.setAdapter(new TaskAdapter(allTask));

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}