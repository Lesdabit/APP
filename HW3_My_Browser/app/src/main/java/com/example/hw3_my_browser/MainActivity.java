package com.example.hw3_my_browser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btnRequest;
    private EditText ET;
    private String url;
    private WebView WB;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRequest = (Button) findViewById(R.id.button);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Click", "Successful");
                ET = (EditText) findViewById(R.id.editTextTextMultiLine);
                url = ET.getText().toString();
                if(url.matches("")) {
                    Toast.makeText(MainActivity.this, "URL is empty", Toast.LENGTH_LONG).show();
                } else {
                    sendAndRequestResponse();
                }
            }
        });

    }

    private void sendAndRequestResponse() {
        Log.d("Response", "Successful");
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Web", "Successful");
                WB = (WebView) findViewById(R.id.webView);
                WB.loadDataWithBaseURL(null, response, "text/html", "UTF-8", null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response", "Error :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }
}