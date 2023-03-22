package com.example.a230315_ex3_downloadingimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.Manifest;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_INTERNET = 1;
    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if(!(conn instanceof HttpURLConnection)) {
            throw new IOException("Not an HTTP connection");
        } try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
                Log.d("DownloadImageTask", "OpenHttpConnection succeeded");
            }
        } catch (Exception e) {
            Log.d("Networking", e.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, REQUEST_INTERNET);
        } else {
            new DownloadImageTask().execute("https://www.freepngimg.com/thumb/butterfly/14-butterfly-png-image.png");
            new DownloadTextTask().execute("https://filesamples.com/samples/document/txt/sample3.txt");
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            return DownloadImage(urls[0]);
        }
        protected void onPostExecute(Bitmap result) {
            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(result);
        }

        private Bitmap DownloadImage(String URL) {
            Bitmap bitmap = null;
            InputStream in = null;
            try {
                in = OpenHttpConnection(URL);
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (IOException e) {
                Log.d("NetworkingActivity", e.getLocalizedMessage());
            }
            return bitmap;
        }
    }

    private class DownloadTextTask extends AsyncTask<String, Void, String> implements com.example.a230315_ex3_downloadingimage.DownloadTextTask {
        @Override
        protected String doInBackground(String... urls) {return DownloadText(urls[0]);}
        @Override
        protected void onPostExecute(String result) {
            TextView TV = (TextView) findViewById(R.id.textView);
            TV.setText(result);
            Log.d("result", result);
        }

        private String DownloadText(String URL) {
            int BUFFER_SIZE = 2000;
            InputStream in=null;

            try {
                in = OpenHttpConnection(URL);
            } catch (IOException e) {
                Log.d("Networking", e.getLocalizedMessage());
                return "";
            }

            InputStreamReader isr = new InputStreamReader(in);
            int charRead;
            String str = "";
            char[] inputBuffer = new char[BUFFER_SIZE];

            try {
                while((charRead = isr.read(inputBuffer)) > 0) {
                    String readString = String.copyValueOf(inputBuffer, 0, charRead);
                    str += readString;
                    inputBuffer = new char[BUFFER_SIZE];
                }
                in.close();
            } catch (Exception e) {
                Log.d("Networking", e.getLocalizedMessage());
                return "";
            }
            return str;
        }
    }
}