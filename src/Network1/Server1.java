package network1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

    public static void main(String[] args) {

        System.out.println("Socket server: ");
        int port = 12345;

        //Create server socket
        //Accept new connection from a client
        try{
            ServerSocket svr = new ServerSocket(port);
            Socket skt = svr.accept();

            InputStream is = skt.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            String msg = dis.readUTF(); //if there is msg
            System.out.println("Message received. >" + msg);
            
            skt.close();
            svr.close();
        }
        catch (IOException e) {
            System.out.println("IO Error");
        }
    
    }

}