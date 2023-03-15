package com.example.a230315_ex2_internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadActivity extends AppCompatActivity {

    private String filename = "myfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Button bkBT = (Button) findViewById(R.id.bk_bt);
        bkBT.setOnClickListener(goBack);
        loadData();
    }

    public void loadData() {
        TextView TV = (TextView) findViewById(R.id.textView);
        FileInputStream in = null;
        StringBuilder sb = new StringBuilder();

        try {
            in = openFileInput(filename);

            int read = 0;
            while ((read = in.read()) != -1) {
                sb.append((char)read);
            }
            TV.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}