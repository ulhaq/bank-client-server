package banking;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static banking.Server.openSocket;


public class EchoThread extends Thread {
    protected Socket openSocket;

    public EchoThread(Socket clientSocket) {
        this.openSocket = clientSocket;
    }

    public void run() {
        Scanner in;
        PrintWriter out;

        try {
            in = new Scanner(openSocket.getInputStream());
            out = new PrintWriter(openSocket.getOutputStream(), true);

            String accNo = new StringBuilder(in.nextLine()).toString();
            System.out.println("Account Number: " + accNo);

            int account = (int) (Math.random() * 500000 + 1);
            out.println("Account " + accNo + " initiated with a total amount of " + account);

            String action = new StringBuilder(in.nextLine()).toString();
            System.out.println("Action: " + action);

            int amount = in.nextInt();
            System.out.println("Amount: " + amount);

            if (action.equalsIgnoreCase("a")) {
                account += amount;
                out.println(amount + " added to your account. Total Amount: " + account);
            }

            if (action.equalsIgnoreCase("s")) {
                if (account - amount < 0) {
                    out.println("Insufficient balance");
                } else {
                    account -= amount;
                    out.println(amount + " subtracted from your account. Total Amount: " + account);
                }
            }

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}