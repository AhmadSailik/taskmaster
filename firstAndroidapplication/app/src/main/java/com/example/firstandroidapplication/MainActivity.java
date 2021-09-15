package com.example.firstandroidapplication;

import static android.os.SystemClock.sleep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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


import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthChannelEventName;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.InitializationStatus;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.appsync.AppSync;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.datastore.generated.model.Todo;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    TaskDatabase taskDatabase;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());


            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {

            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
        Amplify.Auth.signInWithWebUI(

                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );

//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "asailik1993@gmail.com")
//                .build();
//        Amplify.Auth.signUp("ahmadSailik", "123456", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );
//        Amplify.Auth.confirmSignUp(
//                "ahmadSailik",
//                "23456",
//                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        Amplify.Auth.signIn(
//                "ahmadSailik",
//                "123456",
//                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        Button singOut=findViewById(R.id.signOut);
//        singOut.setOnClickListener((v)->{
//            Intent signOut =new Intent(Intent.ACTION_VIEW, Uri.parse("myapp://signout/"));
//            startActivity(signOut);
//        });
        Button singOut=findViewById(R.id.signOut);
        singOut.setOnClickListener((v)->{

            Amplify.Auth.signOut (

                    () ->{
                        Log.i("AuthQuickstart", "Signed out successfully");
                        System.out.println(Log.i("AuthQuickstart", "Signed out successfully"));
                        finish();

//                        Amplify.Auth.signInWithWebUI(
//
//                                this,
//                                result -> Log.i("AuthQuickStart", result.toString()),
//                                error -> Log.e("AuthQuickStart", error.toString())
//                        );

                    } ,
                    error -> Log.e("AuthQuickstart", error.toString())

            );

//            Intent intent=new Intent(MainActivity.this,SettingsPage.class);
//            startActivity(intent);
        });

        Amplify.Auth.fetchAuthSession(
                result ->{
                    Log.i("AmplifyQuickstart", result.toString());
                    if(result.isSignedIn()){
                        TextView textView=findViewById(R.id.users);
                        String welcome="welcome";
                        String userName=Amplify.Auth.getCurrentUser().getUsername();
                        textView.setText(welcome+" "+userName);
                    }

                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );

    }



    @Override
    protected void onStart() {
        super.onStart();


//
//        Team team=Team.builder()
//                .name("Ahmad")
//                .build();
//        Amplify.DataStore.save(team,
//                saved -> Log.i("MyAmplifyApp", "Saved a post."),
//                failure -> Log.e("MyAmplifyApp", "Save failed.", failure)
//        );
//
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
//        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//        System.out.println(Amplify.Auth.getCurrentUser().getUsername());
        Button sitting=findViewById(R.id.sitting);
        sitting.setOnClickListener((view)->{
            Intent sittingUser =new Intent(MainActivity.this,SettingsPage.class);
            startActivity(sittingUser);
        });
        Button button=findViewById(R.id.newTask);
        button.setOnClickListener((v)->{
            Intent oneTask =new Intent(MainActivity.this,AddTask29.class);
            startActivity(oneTask);
        });


        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//        String userName=sharedPreferences.getString("userName","User");

        String teamName=sharedPreferences.getString("teamName","Team");

        TextView textView1=findViewById(R.id.teamName);

        textView1.setText(teamName);
        String teamId=sharedPreferences.getString("teamId","id");
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
                        if(todo.getTeamId().equals(teamId)){
                            Log.i("MyAmplifyApp", todo.getTitle());
                            Log.i("MyAmplifyApp", todo.getBody());
                            Log.i("MyAmplifyApp", todo.getState());
                            allTasks.add(todo);
                            System.out.println(allTasks+"+++++++++++++++++++++++++++++++++++++++");
                        }

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
