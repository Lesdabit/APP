package com.example.a230315_ex2_internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText ET;
    private String filename = "myfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ldBT = (Button) findViewById(R.id.ld_bt);
        ldBT.setOnClickListener(gotoLoadActivity);
        Button svBT = (Button) findViewById(R.id.sv_bt);
        svBT.setOnClickListener(save);

        ET = (EditText) findViewById(R.id.ET);
    }

    private View.OnClickListener gotoLoadActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent(MainActivity.this, LoadActivity.class);
            startActivity(it);
        }
    };

    private View.OnClickListener save = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String str = ET.getText().toString();

            FileOutputStream out = null;
            try {
                deleteFile(filename);
                out = openFileOutput(filename, Context.MODE_APPEND);
                out.write(str.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
}