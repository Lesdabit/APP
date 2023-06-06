/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sendingemail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author lesdabit
 */
public class SendingEmail {
    final int SMTP_PORT = 25;
    String smtp_server = "mailin1.nutn.edu.tw";
    String my_email_addr = "s10959039@stumail.nutn.edu.tw";
    
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
        BufferedReader smtp_in = new BufferedReader(new InputStreamReader(smtp.getInputStream()));
        PrintWriter smtp_out = new PrintWriter(smtp.getOutputStream());
        
        resultCheck(smtp, smtp_in, smtp_out, 220);
        
        String myname = InetAddress.getLocalHost().getHostName();
        sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "HELO " + myname, 250);
        
        sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "MAIL FROM: " + my_email_addr, 250);
        
        for(int i = 0; i < to.length; i++) {
            sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "RCPT TO: " + to[i], 250);
        }
        
        sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "DATA", 354);
        
        smtp_out.print("Subject:" + subject + "\r\n");
        System.out.println("send> " + "Subject:" + subject);
        smtp_out.print("\r\n");
        for(int i = 0;i < msgs.length - 1;i++) {
            smtp_out.print(msgs[i]+"\r\n");
            System.out.println("send> " + msgs[i]);
        }
        sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "\r\n.", 250);
        
        sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "QUIT", 221);
        
        smtp.close();
        
    }

    public void setAddress() {
        String buf = "";
        BufferedReader lineread = new BufferedReader(new InputStreamReader(System.in));
        boolean cont = true;
        
        try {
            while(cont) {
                System.out.println("請輸入郵件伺服器位址: ");
                smtp_server = lineread.readLine();
                System.out.println("請輸入你的郵件位址: ");
                my_email_addr = lineread.readLine();
                System.out.println("郵件伺服器位址: " + smtp_server);
                System.out.println("你的電子郵件位址: " + my_email_addr);
                System.out.println("以上輸入是否正確?(y/n)");
                buf = lineread.readLine();
                if("y".equals(buf)) cont = false;
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public String[] setMsgs() {
        String buf = "";
        BufferedReader lineread  = new BufferedReader(new InputStreamReader(System.in));
        boolean cont = true;
        Vector msgs_list = new Vector();
        String[] msgs = null;
        
        try {
            System.out.println("請輸入要發送的訊息:");
            System.out.println("(用句號結束)");
            while(cont) {
                buf = lineread.readLine();
                msgs_list.addElement(buf);
                if(".".equals(buf)) cont = false;
            }
            msgs = new String[msgs_list.size()];
            msgs_list.copyInto(msgs);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return(msgs);
    }
    
    public void mainproc(String[] args) throws Exception {
        String usage = "java SendingEmail [-s subject] to-addr ...";
        String subject = "";
        Vector<String> to_list = new Vector<>();
        
        for(int i = 0; i < args.length; i++) {
            if("-s".equals(args[i])) {
                i++;
                subject = args[i];
            } else {
                to_list.addElement(args[i]);
            }
            
            if(to_list.size() > 0) {
                try {
                    String[] to = new String[to_list.size()];
                    to_list.copyInto(to);
                    setAddress();
                    String[] msgs = setMsgs();
                    send(subject, to, msgs);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("usage: " + usage);
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        SendingEmail m = new SendingEmail();
        m.mainproc(args);
    }
    
}
