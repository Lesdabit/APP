package com.example.client_app;

import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunClient extends Thread{
    ClientWin window;
    int port;
    String hostname;

    final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

    public BufferedReader reader;
    public PrintWriter writer;
    public boolean loop;

    public RunClient(ClientWin win, String host, int port) {
        this.window = win;
        this.port = port;
        this.hostname = host;
    }

    public void run() {
        ScrollView scrollView = window.findViewById(R.id.scroll_TV);
        TextView receive_TV = window.findViewById(R.id.receive_TV);
        EditText send_ET = window.findViewById(R.id.send_ET);

        String response;
        try {
            Socket socket = new Socket(hostname, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            loop = true;
            while(loop) {
                try {
                    writer.println(queue.take());
                } catch (InterruptedException e) {

                }

                response = reader.readLine();
                response.trim();
                final String res = response;
                window.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        receive_TV.append(res + "\n");
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        send_ET.setText("");
                    }
                });
            }
            socket.close();
        } catch (IOException e) {
            Logger.getLogger(RunClient.class.getName()).log(Level.SEVERE, null, e);
            window.finish();
        }
    }

    public void write(String message) {
        queue.offer(message);
    }
}
