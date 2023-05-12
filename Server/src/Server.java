
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import java.util.logging.Level;
//import java.lang.System.Logger;
//import java.lang.System.Logger.Level;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author lesdabit
 */
public class Server extends javax.swing.JFrame {
    private final int port = 2345;
    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
        try {
            new RunServer(port, this).start();
        } catch (IOException ex){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class RunServer extends Thread {
        private Server window;
        private ServerSocket server_socket;
        private int port;
        private int No = 0;
        
        public RunServer(int port, Server win) throws IOException {
            this.port = port;
            this.window = win;
            server_socket = new ServerSocket(port);
        }
        
        synchronized int getNo() {
            return ++No;
        }
        
        public void run() {
            Socket socket;
            window.jTextArea1.setText("Server is running \n");
            while(true) {
                try {
                    socket = server_socket.accept();
                    int Id = getNo();
                    window.jTextArea1.append(("Client " + Id + " connected \n"));
                    new Handle_Computing(socket, this, Id).start();
                } catch(IOException e) {
                    Logger.getLogger(RunServer.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        
        public Server getWindow() {
            return this.window;
        }
    }
    
    public class Handle_Computing extends Thread {
        private Socket socket;
        private RunServer server;
        private int Id;
        
        public Handle_Computing(Socket socket, RunServer server, int Id) {
            this.socket = socket;
            this.server = server;
            this.Id = Id;
        }
        
        public void run() {
            Scanner input = null;
            PrintWriter output = null;
                
            try {
                input = new Scanner(socket.getInputStream());
                output = new PrintWriter(socket.getOutputStream(), true);
                String message;
                do {
                    message = input.nextLine();
                    String[] message_s = message.split(" ");
                    if(!message.equals("CLOSE")) {
                        
                        boolean isNumeric_0 =  message_s[0].matches("[+-]?\\d*(\\.\\d+)?");
                        boolean isNumeric_1 =  message_s[1].matches("[+-]?\\d*(\\.\\d+)?");
                        if(!isNumeric_0 || !isNumeric_1) {
                            server.getWindow().jTextArea1.append("Client " + Id + " Error, one of the input is not a number\n");
                            output.println("The result: Error ! Can not calculate ! Please enter again" );
                        } else {
                            double a = Double.parseDouble(message_s[0]);
                            double b = Double.parseDouble(message_s[1]);
                            String op = message_s[2];
                            double result = 0;
                            boolean can = true;

                            switch(op) {
                                case "%":
                                    result = a % b;
                                    break;
                                case "+":
                                    result = a + b;
                                    break;
                                case "-":
                                    result = a - b;
                                    break;
                                case "/":
                                    if(b == 0) {
                                        can = false;
                                        break;
                                    }
                                    result = a / b;
                                    break;
                                case "*":
                                    result = a * b;
                                    break;
                                default:
                                    break;
                            }
                            
                            message = new String(message_s[0] + " " + message_s[2] + " " + message_s[1]);

                            if(can) {
                                server.getWindow().jTextArea1.append("Client " + Id + " sent: " + message + "\n");
                                output.println("The result: " + message + " = " + result);
                            } else {
                                server.getWindow().jTextArea1.append("Client " + Id + " Error, number b is 0\n");
                                output.println("The result: Error ! Can not calculate ! Please enter again" );
                            }
                        }
                        
                    } else {
                        server.getWindow().jTextArea1.append("Client " + Id + " sent: " + message + "\n");
                        output.println("Echo back your message: " + message);
                    }
                } while(!message.equals("CLOSE"));
                server.getWindow().jTextArea1.append("Client disconnected.\n");
            } catch (IOException e) {
                Logger.getLogger(Handle_Computing.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
