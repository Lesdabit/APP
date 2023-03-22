package com.example.a230322_ex1_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Button btnRequest;
    private EditText ET;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://www.google.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRequest = (Button) findViewById(R.id.button);
        ET = (EditText) findViewById(R.id.editTextTextMultiLine);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAndRequestResponse();
            }
        });
    }

    private void sendAndRequestResponse() {
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        //StringRequest initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ET.setText(response.toString());

                try {
                    FileOutputStream outfile = openFileOutput("Result.txt", Context.MODE_APPEND);
                    outfile.write(response.getBytes(StandardCharsets.UTF_8));
                    outfile.close();
                    parsing_csvFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    private void parsing_csvFile() {
        try {
            File file = getBaseContext().getFileStreamPath("out.csv");
            FileInputStream fstream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while((line = br.readLine()) != null) {
                String[] apartment = line.split(",");
                TextView TV = (TextView) findViewById(R.id.textView);
                TV.setText("street=" + apartment[0] + ", city=" + apartment[1] + ", size=" + apartment[2] +
                        ", state=" + apartment[3] + ", beds=" + apartment[4]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}