package network2;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

    public static void main(String[] args) {

        System.out.println("Socket server: ");
        int port = 9999;

        //Create server socket
        //Accept new connection from a client
        try{
            ServerSocket svr = new ServerSocket(port);
            Socket skt = svr.accept();

            InputStream is = skt.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            //Read input from client
            String fromClient = dis.readUTF();

            //Server output
            while (!fromClient.equalsIgnoreCase("close") && fromClient != null) {    
                System.out.println("Received message from client: "+ fromClient);
                fromClient = dis.readUTF(); //read the next line from input stream
            }
            
            System.out.println("Closing socket");
            skt.close();
            svr.close();
        } 
        catch (IOException e) {
            System.out.println("IO Error");
        }
    
    }

}