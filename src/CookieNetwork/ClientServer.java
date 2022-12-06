package CookieNetwork;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientServer {

    public static void main(String[] args) {
        
        /*
        Server <program> <server> <port> <cookie-file.txt>
        <java -cp fortunecookie.jar> <fc.Server> <12345> <cookie_file.txt>
        Client <program> <client> <host> <port>
        <java -cp fortunecookie.jar> <fc.Client> <locahost> <12345>
        */
        
        String usage = """
        Server
        <program> <server> <port> <cookie-file.txt>
        Client
        <program> <client> <host> <port>
        """;
     
        if((args.length)!= 3) {
            System.out.println("Incorrect inputs. Please check the following usage.");
            System.out.println(usage);
            return;
        }
        
        String type = args[0];
        if(type.equalsIgnoreCase("Server")) {
            int port = Integer.parseInt(args[1]);
            String fileName = args[2];
            StartServer(port, fileName);
        } else if (type.equalsIgnoreCase("Client")) {
            String hostName = args[1];
            int port = Integer.parseInt(args[2]);
            StartClient(hostName, port);
        } else {
            System.out.println("Incorrect inputs.");
        }
    }

    public static void StartServer(int port, String fileName) {
        ServerSocket svrskt;
        try {
            svrskt = new ServerSocket(port);
            Socket socket = svrskt.accept();

            //Server input and output stream
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            while (true) {
                String fromClient = dis.readUTF();

                if (fromClient.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Message from client: " + fromClient);
                if (fromClient.equalsIgnoreCase("get-cookie")) {
                    // Send a random cookie from the file
                    dos.writeUTF("Dummy cookie..");
                    // Implement this class
                    // doc.writeUTF(new CookieFile().getRandomCookie())
                    dos.flush();
                } else {
                    // Send a msg, "Invalid command from server"
                    dos.writeUTF("From server: Invalid Command");
                    dos.flush();
                }
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    public static void StartClient(String host, int port) {
        try {
            Socket skt = new Socket(host, port);
            
            //Client input and output stream
            DataInputStream dis = new DataInputStream(new BufferedInputStream(skt.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(skt.getOutputStream()));

            Scanner sc = new Scanner(System.in);
            boolean stop = false;

            while (!stop) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("Exit")) {
                    dos.writeUTF("Exit");
                    dos.flush();
                    stop = true;
                    break;
                }

                dos.writeUTF(line);
                dos.flush();

                String fromServer = dis.readUTF();
                System.out.println("Response from server !" + fromServer);

                skt.close();
                sc.close();
            
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}