package TCPEchoClient;

import java.net.InetAddress;  
import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lesdabit
 */
public class TCPEchoClient {
    private static InetAddress host;
    private static final int PORT = 1234;
    private static String s;
    
    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }
    
    private static void accessServer() {
        Socket link = null;
        
        try {
            link = new Socket(host, PORT);
            Scanner input = new Scanner(link.getInputStream());
            
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            Scanner userEntry = new Scanner(System.in);
          
            String response;
            do {
                System.out.println("Enter two numbers and a operand > ");
                s = userEntry.nextLine();
                output.println(s);
                if(s.equals("CLOSE")) break;
                response = input.nextLine();
                System.out.println("\nRESULT> " + response);
            } while (!s.equals("CLOSE"));
            
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

    Object getGreeting() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
