package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();
        String title=intent.getExtras().getString("title","title");
        String body=intent.getExtras().getString("body","body");
        String state=intent.getExtras().getString("state","state");
        TextView textViews=findViewById(R.id.titleOfDetails);
        TextView textViews1=findViewById(R.id.textTitle);
        TextView textViews2=findViewById(R.id.textBody);
        TextView textViews3=findViewById(R.id.textState);
        textViews.setText(title);
        textViews1.setText(title);
        textViews2.setText(body);
        textViews3.setText(state);
        TextView textView=findViewById(R.id.LoremIpsum);
        textView.setText("Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloremque aut molestiae rem, non beatae modi veritatis ea nulla voluptatibus illum reiciendis amet quae! Pariatur, praesentium perspiciatis totam mollitia reprehenderit vitae.");

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}