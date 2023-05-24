/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sendingemail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author lesdabit
 */
public class SendingEmail {
    final int SMTP_PORT = 25;
    String smtp_server = "";
    String my_email_addr = "";
    
    public void sendCommandAndResultCheck(Socket smtp, BufferedReader smtp_in, PrintWriter smtp_out, String command, int success_code) throws IOException {
        smtp_out.print(command + "\r\n");
        smtp_out.flush();
        System.out.println("send> " + command);
        resultCheck(smtp, smtp_in, smtp_out, success_code);
    }
    
    public void resultCheck(Socket smtp, BufferedReader smtp_in, PrintWriter smtp_out, int success_code) throws IOException {
        String res = smtp_in.readLine();
        System.out.println("recv> " + res);
        if(Integer.parseInt(res.substring(0,3)) != success_code) {
            smtp.close();
            throw new RuntimeException(res);
        }
    }
    
    public void send(String subject, String[] to, String[] msgs) throws Exception {
        Socket smtp = new Socket(smtp_server, SMTP_PORT);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
