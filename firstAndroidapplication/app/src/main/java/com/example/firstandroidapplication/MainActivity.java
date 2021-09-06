package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TaskDatabase taskDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Button button=findViewById(R.id.newTask);
        button.setOnClickListener((v)->{
            Intent oneTask =new Intent(MainActivity.this,AddTask29.class);
            startActivity(oneTask);
        });
        taskDatabase =  Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "taskDatas").allowMainThreadQueries().build();
        List<Task> allTasks=taskDatabase.taskDao().getAll();
//        ArrayList<Task> allTasks=new ArrayList<Task>();
//        allTasks.add(new Task("jop","still research","in progress"));
//        allTasks.add(new Task("lab","add All task","complete"));
//        allTasks.add(new Task("CSS","course CSS","assigned"));
        RecyclerView allTaskRecyclerView=findViewById(R.id.listOfButton);
//        allTaskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allTaskRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        allTaskRecyclerView.setAdapter(new vickAdapter(allTasks));

        Button one=findViewById(R.id.one);
        Button two=findViewById(R.id.two);
        Button three=findViewById(R.id.three);
        one.setOnClickListener((v)-> {
            String name=one.getText().toString();
            Intent oneTask =new Intent(MainActivity.this,DetailPage.class);
            oneTask.putExtra("title",name);
            startActivity(oneTask);
        });
        two.setOnClickListener((v)-> {
            String name=two.getText().toString();
            Intent oneTask =new Intent(MainActivity.this,DetailPage.class);
            oneTask.putExtra("title",name);
            startActivity(oneTask);
        });
        three.setOnClickListener((v)-> {
            String name=three.getText().toString();
            Intent oneTask =new Intent(MainActivity.this,DetailPage.class);
            oneTask.putExtra("title",name);
            startActivity(oneTask);
        });

        String welcome="welcome";
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName=sharedPreferences.getString("userName","User");
        TextView textView=findViewById(R.id.users);
        textView.setText(welcome+" "+userName);
        Button sitting=findViewById(R.id.sitting);
        sitting.setOnClickListener((view)->{
            Intent sittingUser =new Intent(MainActivity.this,SettingsPage.class);
            startActivity(sittingUser);
        });
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
