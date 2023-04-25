import java.io.*;
import java.net.*;
import java.util.*;

public class TCPechoServer_MTH {
    private static final int PORT = 1234;
    private static boolean serverRunning = true;

    public static void main(String[] args) {
        System.out.println("Opening port...\n");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (serverRunning) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                Scanner input = new Scanner(clientSocket.getInputStream());
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String message;

                do {
                    if(!input.hasNext()) break;
                    message = input.nextLine();
                    if(message.equals("CLOSE")) break;
                    System.out.println(message);
                    String[] message_s = message.split(",");
                    double a = Double.parseDouble(message_s[0]);
                    double b = Double.parseDouble(message_s[1]);
                    String op = message_s[2];
                    double result = 0;

                    switch(op) {
                        case "+":
                            result = a + b;
                            break;
                        case "-":
                            result = a - b;
                            break;
                        case "/":
                            result = a / b;
                            break;
                        case "*":
                            result = a * b;
                            break;
                        default:
                            break;
                    }
                    output.println(result);
                } while (true);
            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            } finally {
                try {
                    System.out.println("\n* Closing connection... *");
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}