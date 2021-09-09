package com.example.firstandroidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    TaskDatabase taskDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {

            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        Button button=findViewById(R.id.newTask);
        button.setOnClickListener((v)->{
            Intent oneTask =new Intent(MainActivity.this,AddTask29.class);
            startActivity(oneTask);
        });
        RecyclerView allTaskRecyclerView=findViewById(R.id.listOfButton);
        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        allTaskRecyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });
        ArrayList<Todo> allTasks=new ArrayList<Todo>();
        Amplify.API.query(
                ModelQuery.list(Todo.class),

                response -> {
                    System.out.println(response+"------------------------------------------------");
                    for (Todo todo : response.getData()) {
                        Log.i("MyAmplifyApp", todo.getTitle());
                        Log.i("MyAmplifyApp", todo.getBody());
                        Log.i("MyAmplifyApp", todo.getState());
                        allTasks.add(todo);
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
