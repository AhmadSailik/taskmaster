package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button save=findViewById(R.id.save);
        EditText editText=findViewById(R.id.editUserName);
        save.setOnClickListener((view)->{
            String userName=editText.getText().toString();
            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(SettingsPage.this);
            SharedPreferences.Editor sharedPreferencesEditor=sharedPreferences.edit();
            sharedPreferencesEditor.putString("userName",userName);
            sharedPreferencesEditor.apply();
            Intent sittingUser =new Intent(SettingsPage.this,MainActivity.class);
            startActivity(sittingUser);
        });
    }
}