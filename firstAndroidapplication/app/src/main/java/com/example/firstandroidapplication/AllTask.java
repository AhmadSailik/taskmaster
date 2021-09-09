package com.example.firstandroidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class AllTask extends AppCompatActivity {
//    TaskDatabase taskDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView allTaskRecyclerView=findViewById(R.id.listOfTask);
        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        allTaskRecyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });
        ArrayList<Todo> taskList=new ArrayList<Todo>();
        Amplify.API.query(
                ModelQuery.list(Todo.class),

                response -> {
                    System.out.println(response+"------------------------------------------------");
                    for (Todo todo : response.getData()) {
                        Log.i("MyAmplifyApp", todo.getTitle());
                        Log.i("MyAmplifyApp", todo.getBody());
                        Log.i("MyAmplifyApp", todo.getState());
                        taskList.add(todo);
//                        System.out.println(allTasks+"+++++++++++++++++++++++++++++++++++++++");
                    }
                    handler.sendEmptyMessage(1);
                    System.out.println("**********************************************");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
//        taskDatabase =  Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "taskDatas").allowMainThreadQueries().build();
//        List<Task> allTasks=taskDatabase.taskDao().getAll();

//        allTasks.add(new Task("jop","still research","in progress"));
//        allTasks.add(new Task("lab","add All task","complete"));
//        allTasks.add(new Task("CSS","course CSS","assigned"));

//        allTaskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        allTaskRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
//        allTaskRecyclerView.setAdapter(new vickAdapter(allTasks));
//        taskDatabase =  Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "taskDatas").allowMainThreadQueries().build();
//        List<Task>taskList=taskDatabase.taskDao().getAll();
//        ArrayList<Task>allTask=new ArrayList<Task>();
//        allTask.add(new Task("jop","still research","in progress"));
//        allTask.add(new Task("lab","add All task","complete"));
//        allTask.add(new Task("CSS","course CSS","assigned"));

        allTaskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allTaskRecyclerView.setAdapter(new TaskAdapter(taskList));

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}