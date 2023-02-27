package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner exercises;
    TextView txv;
    TextView result;
    EditText kilo;
    EditText hour;
    double calorie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView txv = (TextView) findViewById(R.id.txv);
        exercises = (Spinner) findViewById(R.id.exercises);
        txv = (TextView) findViewById(R.id.display_cal);
        result = (TextView) findViewById(R.id.result);
        kilo = (EditText) findViewById(R.id.kilo);
        hour = (EditText) findViewById(R.id.hour);

        exercises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String newItem = exercises.getSelectedItem().toString();
                switch (newItem) {
                    case "慢跑":
                        txv.setText("消耗熱量(仟卡/公斤/小時)：3");
                        calorie = 3;
                        break;
                    case "游泳":
                        txv.setText("消耗熱量(仟卡/公斤/小時)：4");
                        calorie = 4;
                        break;
                    case "飛輪":
                        txv.setText("消耗熱量(仟卡/公斤/小時)：2");
                        calorie = 2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button cal = (Button) findViewById(R.id.cal_button);
        ButtonHandler blistener = new ButtonHandler();
        cal.setOnClickListener(blistener);
    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            double kilo_int = Double.parseDouble(kilo.getText().toString());
            double hour_int = Double.parseDouble(hour.getText().toString());
            result.setText("消耗熱量" + (int)(kilo_int*hour_int*calorie) + "仟卡");
        }
    }

}