package cookienetwork;

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
        javac -sourcepath src -d classes src/cookienetwork/*.java
        Server
        java -cp classes cookienetwork.ClientServer server 12345 cookie_file.txt
        Client
        java -cp classes cookienetwork.ClientServer client localhost 12345
        */
        
        String instructions = """
        Server
        <program> <server> <port> <cookie-file.txt>
        Client
        <program> <client> <host> <port>
        """;
     
        if((args.length)!= 3) {
            System.out.println("Incorrect inputs. Please check the following usage.");
            System.out.println(instructions);
            return;
        }
        
        String type = args[0];
        if(type.equalsIgnoreCase("Server")) {
            int port = Integer.parseInt(args[1]);
            String fileName = args[2];
            StartServer(port, fileName); //call method StartServer
        }
        else if (type.equalsIgnoreCase("Client")) {
            String hostName = args[1];
            int port = Integer.parseInt(args[2]);
            StartClient(hostName, port); //call method StartClient
        }
        else {
            System.out.println("Incorrect inputs.");
        }

    }

    //Method to launch Server
    public static void StartServer(int port, String fileName) {
  
        try {
            ServerSocket svrskt = new ServerSocket(port);
            Socket socket = svrskt.accept();

            //Server I/O stream
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            while (true) {
                String fromClient = dis.readUTF();

                if (fromClient.equalsIgnoreCase("close")) {
                    break;
                }
                else if (fromClient.equalsIgnoreCase("get-cookie")) {
                    System.out.println("Message from Client: " + fromClient);

                    // Send a random cookie from the file
                    String cookie = CookieFile.GetRandomCookie(fileName);
                    System.out.println("cookie-text " + cookie);
                    dos.writeUTF(cookie);
                    dos.flush();
                }
                else {
                    System.out.println("Message from Client: " + fromClient);
                    dos.writeUTF("From Server: Invalid Command");
                    dos.flush();
                }
            }

            socket.close();
            svrskt.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }        
    
    }

    public static void StartClient(String host, int port) {
    
        try {
            Socket skt = new Socket(host, port);
            
            //Client I/O stream
            DataInputStream dis = new DataInputStream(new BufferedInputStream(skt.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(skt.getOutputStream()));

            Scanner sc = new Scanner(System.in);
            boolean stop = false;

            while (!stop) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("close")) {
                    dos.writeUTF("Closing.");
                    dos.flush();
                    stop = true;
                    break;
                }
                else {
                    dos.writeUTF(line);
                    dos.flush();
                }

                String fromServer = dis.readUTF();
                System.out.println("Response from Server : " + fromServer);
            }

            sc.close();
            skt.close();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}