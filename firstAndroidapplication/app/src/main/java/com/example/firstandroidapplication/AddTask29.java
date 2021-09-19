package com.example.firstandroidapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

public class AddTask29 extends AppCompatActivity {
//    TaskDatabase taskDatabase;
//    TaskDao taskDao;
//File exampleFile;
private EditText title;
private String team ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task29);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title=findViewById(R.id.editTextTitile);



    }



    @Override
    protected void onStart() {
        super.onStart();
        EditText body=findViewById(R.id.editTextBody);
        EditText state=findViewById(R.id.editTextState);
        Intent recieve=getIntent();
        if(recieve.getType()!=null&&recieve.getType().equals("text/plain")){
            System.out.println("$$$$$$$$$$$$$$$$$$$"+recieve.getExtras().get(Intent.EXTRA_TEXT).toString());
            body.setText(recieve.getExtras().get(Intent.EXTRA_TEXT).toString());
        }



        Button addImage=findViewById(R.id.addImage);
        addImage.setOnClickListener((v)->{
            Intent chooseFile=new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            chooseFile=Intent.createChooser(chooseFile,"Choose a file");
            startActivityForResult(chooseFile,1234);
        });

        RadioButton radioButton =findViewById(R.id.radioButtontask1);
        radioButton.setText("Ahmad");
        radioButton.setPrivateImeOptions("733c530c-c007-4269-a2f5-1bd520b45c8d");
        radioButton.setOnClickListener((v)->{
            team =radioButton.getPrivateImeOptions();
        });
        RadioButton radioButton1 =findViewById(R.id.radioButtontask2);
        radioButton1.setText("Noor");
        radioButton1.setPrivateImeOptions("5876219e-ccb7-4ae3-ba05-347501361848");
        radioButton1.setOnClickListener((v)->{
            team =radioButton1.getPrivateImeOptions();
        });
        RadioButton radioButton2 =findViewById(R.id.radioButtontask3);
        radioButton2.setText("Salah");
        radioButton2.setPrivateImeOptions("2703d85a-b943-4366-bff6-926a6aae9927");
        radioButton2.setOnClickListener((v)->{
            team =radioButton2.getPrivateImeOptions();
        });
        Button button=findViewById(R.id.submit);

        button.setOnClickListener((v)->{
//            Task task=new Task(title.getText().toString(),body.getText().toString(),state.getText().toString());
//            taskDatabase =  Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "taskDatas").allowMainThreadQueries().build();
//            taskDao=taskDatabase.taskDao();
//            taskDao.isertrAll(task);
//            private void uploadFile() {
//            onActivityResult(int requestCode, int resultCode, @Nullable Intent data)



//            }
            Todo todo = Todo.builder()
                    .teamId(team)
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
            goToMain.putExtra("imageName",title.getText().toString());
            startActivity(goToMain);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String key=title.getText().toString();
        File exampleFile = new File(getApplicationContext().getFilesDir(), "title");
        try {
            InputStream inputStream=getContentResolver().openInputStream(data.getData());
            OutputStream outputStream=new FileOutputStream(exampleFile);
            byte[]buf=new byte[1024];
            int len;
            while ((len=inputStream.read(buf))>0){
                outputStream.write(buf,0,len);
            }
            inputStream.close();
            outputStream.close();

        } catch (Exception exception) {
            Log.e("MyAmplifyApp", "Upload failed", exception);
        }

        Amplify.Storage.uploadFile(
                key,
                exampleFile,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}