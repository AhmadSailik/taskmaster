package com.example.firstandroidapplication;

import static android.os.SystemClock.sleep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
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


import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", userStateDetails.getUserState().toString());
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

    //    TaskDatabase taskDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{");
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//
////               public void onRequestPermissionsResult(int requestCode, String[] permissions,
////                                                      int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }

//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        System.out.println("))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            System.out.println(location+"))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
//                            // Logic to handle location object
//                        }
//                    }
//                });
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            getPinpointManager(getApplicationContext());


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
                            Log.i("MyAmplifyApp", todo.getLatitude());
                            Log.i("MyAmplifyApp", todo.getLongitude());
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
