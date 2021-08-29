package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddTasks extends AppCompatActivity {
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView textView=findViewById(R.id.textView5);
        textView.setText("Total Task: "+counter);
        Button sumbit=findViewById(R.id.AddTask);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Submit",Toast.LENGTH_LONG).show();
                counter++;
                textView.setText("Total Task: "+counter);


            }
        });

    }
}