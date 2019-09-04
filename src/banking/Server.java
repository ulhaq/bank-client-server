package banking;

import banking.EchoThread;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server {
    public static final int PORT = 6666;
    public static ServerSocket serverSocket = null;
    public static Socket openSocket = null;

    public static Socket configureServer() throws UnknownHostException, IOException {
        while (true) {
            String serverIP = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Server ip: " + serverIP);

            serverSocket = new ServerSocket(PORT);

            while (true) {
                try {
                    openSocket = serverSocket.accept();
                    System.out.println("Server accepts requests at: " + openSocket);

                    new EchoThread(openSocket).start();
                } catch (IOException e) {
                    System.out.println("I/O error: " + e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        configureServer();
    }
}