package com.example.a230315_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveBT = (Button) findViewById(R.id.save_bt);
        Button loadBT = (Button) findViewById(R.id.load_bt);

        final EditText LastNameET = (EditText) findViewById(R.id.LastNameET);
        final EditText FirstNameET = (EditText) findViewById(R.id.FirstNameET);
        final TextView TotalNameTV = (TextView) findViewById(R.id.TotalNameTV);

    }


}