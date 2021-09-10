package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;

public class AddTask29 extends AppCompatActivity {
//    TaskDatabase taskDatabase;
//    TaskDao taskDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task29);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        Team team=Team.builder()
//                .name("Ahmad")
//                .build();
//        Amplify.DataStore.save(team,
//                saved -> Log.i("MyAmplifyApp", "Saved a post."),
//                failure -> Log.e("MyAmplifyApp", "Save failed.", failure)
//        );
//        Team team1=Team.builder()
//                .name("Noor")
//                .build();
//        Amplify.DataStore.save(team1,
//                saved -> Log.i("MyAmplifyApp", "Saved a post."),
//                failure -> Log.e("MyAmplifyApp", "Save failed.", failure)
//        );
//        Team team2=Team.builder()
//                .name("Salah")
//                .build();
//        Amplify.DataStore.save(team2,
//                saved -> Log.i("MyAmplifyApp", "Saved a post."),
//                failure -> Log.e("MyAmplifyApp", "Save failed.", failure)
//        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        EditText title=findViewById(R.id.editTextTitile);
        EditText body=findViewById(R.id.editTextBody);
        EditText state=findViewById(R.id.editTextState);
        Team team=Team.builder()
                .name("Ahmad")
                .build();
        Amplify.DataStore.save(team,
                saved -> Log.i("MyAmplifyApp", "Saved a post."),
                failure -> Log.e("MyAmplifyApp", "Save failed.", failure)
        );

        Button button=findViewById(R.id.submit);

        button.setOnClickListener((v)->{
//            Task task=new Task(title.getText().toString(),body.getText().toString(),state.getText().toString());
//            taskDatabase =  Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "taskDatas").allowMainThreadQueries().build();
//            taskDao=taskDatabase.taskDao();
//            taskDao.isertrAll(task);
            Todo todo = Todo.builder()
                    .teamId(team.getId())
                    .title(title.getText().toString())
                    .body(body.getText().toString())
                    .state(state.getText().toString())
                    .build();
            System.out.println("#########################");
            Amplify.DataStore.save(todo,
                    saved -> Log.i("MyAmplifyApp", "Saved a post."),
                    failure -> Log.e("MyAmplifyApp", "Save failed.", failure)
            );
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