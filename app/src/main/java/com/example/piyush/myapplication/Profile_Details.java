package com.example.piyush.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile_Details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__details);

        TextView name=(TextView)findViewById(R.id.username);
        TextView email=(TextView)findViewById(R.id.EmailID);

        ArrayList<String> passedArg = getIntent().getExtras().getStringArrayList("arg");

        name.setText(passedArg.get(0));
        email.setText(passedArg.get(1));

    }
}
