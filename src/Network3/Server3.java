package network3;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
    
    public static void main(String[] args) {

        System.out.println("Socket server: ");
        int port = 12000;

        try{
            ServerSocket svr = new ServerSocket(port);
            Socket skt = svr.accept();

            //Output stream
            DataInputStream dis = new DataInputStream(new BufferedInputStream(skt.getInputStream()));
            
            String line = dis.readUTF();
            while (!line.equalsIgnoreCase("EOF") && 
            line != null) {
                try{
                    System.out.println(line);
                    line = dis.readUTF();
                }
                catch (EOFException e) {
                System.out.println("Reached End of File");
                break;
                }
            }

            skt.close();
            svr.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    
    }
}
