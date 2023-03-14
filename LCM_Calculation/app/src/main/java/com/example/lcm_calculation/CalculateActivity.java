package com.example.lcm_calculation;

//import static com.example.lcm_calculation.MainActivity.result;

import androidx.appcompat.app.AppCompatActivity;
//import static com.example.lcm_calculation.MainActivity.a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalculateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        TextView resultString = (TextView) findViewById(R.id.resultTV);
        Intent it = getIntent();
        String result = it.getStringExtra("result");
        resultString.setText(result);
    }

    public void goBack(View v) {
        finish();
    }
}