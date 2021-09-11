package com.example.firstandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SettingsPage extends AppCompatActivity {
private String team;
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
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(SettingsPage.this);
        SharedPreferences.Editor sharedPreferencesEditor=sharedPreferences.edit();
        save.setOnClickListener((view)->{
            String userName=editText.getText().toString();
            sharedPreferencesEditor.putString("userName",userName);
            sharedPreferencesEditor.apply();
            Intent sittingUser =new Intent(SettingsPage.this,MainActivity.class);
            startActivity(sittingUser);
        });
        RadioButton radioButton =findViewById(R.id.radioButtonsetting1);
        radioButton.setText("Ahmad");
        radioButton.setPrivateImeOptions("733c530c-c007-4269-a2f5-1bd520b45c8d");
        radioButton.setOnClickListener((v)->{
            team =radioButton.getPrivateImeOptions();
            sharedPreferencesEditor.putString("teamName",radioButton.getText().toString());
            sharedPreferencesEditor.putString("teamId",team);
            sharedPreferencesEditor.apply();
        });
        RadioButton radioButton1 =findViewById(R.id.radioButtonsetting2);
        radioButton1.setText("Noor");
        radioButton1.setPrivateImeOptions("5876219e-ccb7-4ae3-ba05-347501361848");
        radioButton1.setOnClickListener((v)->{
            team =radioButton1.getPrivateImeOptions();
            sharedPreferencesEditor.putString("teamName",radioButton1.getText().toString());
            sharedPreferencesEditor.putString("teamId",team);
            sharedPreferencesEditor.apply();
        });
        RadioButton radioButton2 =findViewById(R.id.radioButtonsetting3);
        radioButton2.setText("Salah");
        radioButton2.setPrivateImeOptions("2703d85a-b943-4366-bff6-926a6aae9927");
        radioButton2.setOnClickListener((v)->{
            team =radioButton2.getPrivateImeOptions();
            sharedPreferencesEditor.putString("teamName",radioButton2.getText().toString());
            sharedPreferencesEditor.putString("teamId",team);
            sharedPreferencesEditor.apply();
        });
    }
}