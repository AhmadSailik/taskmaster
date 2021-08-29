package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Button addTask=findViewById(R.id.button);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"The button was clicked",Toast.LENGTH_LONG).show();
                Intent addTask=new Intent(MainActivity.this,AddTasks.class);
                startActivity(addTask);
            }
        });
        Button allTask=findViewById(R.id.button2);
        allTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"this button for all task",Toast.LENGTH_LONG).show();
                Intent allTask=new Intent(MainActivity.this,AllTask.class);
                startActivity(allTask);
            }
        });
//        TextView heading=findViewById(R.id.textView);
//        String viewHeadind=heading.getText().toString();
//        setContentView(R.layout.activity_main);
    }
}
