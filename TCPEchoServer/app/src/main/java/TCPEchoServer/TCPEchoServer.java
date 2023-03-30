package TCPEchoServer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lesdabit
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class TCPEchoServer{
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;
    private static String s;
    private static double a, b;
    private static String op;
    
    public static void main(String[] args) {
        System.out.println("Opening port...\n");
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
        
        do {
            handleClient();
        } while (true);
    }
    
    private static void handleClient() {
        Socket link = null;
        
        try {
            link = serverSocket.accept();
            
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            String message;
            
            do {
                if(!input.hasNext()) break;
                message = input.nextLine();
                if(message.equals("CLOSE")) break;
                System.out.println(message);
                String[] message_s = message.split(",");
                a = Double.parseDouble(message_s[0]);
                b = Double.parseDouble(message_s[1]);
                op = message_s[2];
                double result = 0;
                
                switch(op) {
                    case "+":
                        result = a+b;
                        break;
                    case "-":
                        result = a-b;
                        break;
                    case "/":
                        result = a/b;
                        break;
                    case "*":
                        result = a*b;
                        break;
                    default:
                        break;
                }
                output.println(result);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("\n* Closing connection... *");
                link.close();
            } catch (IOException e) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }

    public Object getGreeting() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}