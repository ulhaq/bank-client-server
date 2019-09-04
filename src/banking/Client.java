package banking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

class Client {
    static Scanner cin;
    static PrintWriter out;
    static Scanner in;

    public static int PORT = 6666;

    public static String request, response;

    public static void main(String[] args) throws IOException {
        InetAddress ip = null;
        Socket socket = null;

        if (args.length == 2)
            socket = new Socket(args[0], parseInt(args[1]));
        else {
            System.err.println("Server data missing");
            ip = InetAddress.getByName(null);
            socket = new Socket(ip, PORT);
        }
        System.out.println("Socket: " + socket);

        try (Scanner cin = new Scanner(System.in); PrintWriter out = new PrintWriter(socket.getOutputStream(), true); Scanner in = new Scanner(socket.getInputStream());) {
            System.out.println("Client up and running");

            System.out.println("Enter Account No.");
            out.println(cin.nextLine());

            System.out.println("Would you like to add or subtract money? [A/S]");
            out.println(cin.nextLine());

            System.out.println("Enter the amount");
            out.println(cin.nextLine());

            while (in.hasNextLine()) {
                response = in.nextLine();
                System.out.println(response);
            }
        } catch (UnknownHostException e) {
            System.err.println("No host " + ip);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No I/O connection to " + ip);
            System.exit(1);
        } finally {
            socket.close();
            System.out.println("Transaction completed. Connection closed.");
        }
    }
}