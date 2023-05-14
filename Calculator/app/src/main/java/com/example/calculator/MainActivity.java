package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button one_bt, two_bt, three_bt, four_bt, five_bt, six_bt, seven_bt, eight_bt, nine_bt, mod_bt, clear_bt, c_bt, delete_bt;
    Button divide_bt, multiply_bt, minus_bt, plus_bt, equal_bt;
    TextView calculate_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}