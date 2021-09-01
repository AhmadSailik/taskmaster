package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class AddTask29 extends AppCompatActivity {
    TaskDatabase taskDatabase;
    TaskDao taskDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task29);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    protected void onStart() {
        super.onStart();
    EditText title=findViewById(R.id.editTextTitile);
        EditText body=findViewById(R.id.editTextBody);
        EditText state=findViewById(R.id.editTextState);
        Button button=findViewById(R.id.submit);

        button.setOnClickListener((v)->{
            Task task=new Task(title.getText().toString(),body.getText().toString(),state.getText().toString());
            taskDatabase =  Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "taskDatas").allowMainThreadQueries().build();
            taskDao=taskDatabase.taskDao();
            taskDao.isertrAll(task);
            Intent goToMain=new Intent(AddTask29.this,MainActivity.class);
            startActivity(goToMain);
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}