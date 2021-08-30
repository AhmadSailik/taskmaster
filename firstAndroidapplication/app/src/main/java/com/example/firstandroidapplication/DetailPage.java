package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();
        String tile=intent.getExtras().getString("title");
        TextView textViews=findViewById(R.id.titleOfDetails);
        textViews.setText(tile);
        TextView textView=findViewById(R.id.LoremIpsum);
        textView.setText("Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloremque aut molestiae rem, non beatae modi veritatis ea nulla voluptatibus illum reiciendis amet quae! Pariatur, praesentium perspiciatis totam mollitia reprehenderit vitae.");

    }
}