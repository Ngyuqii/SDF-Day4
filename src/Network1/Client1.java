package network1;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {

    public static void main(String[] args) {

        int port = 12345;

        try {
            Socket cs = new Socket ("localhost", port);

            //Get the I/O Stream
            OutputStream os = cs.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            dos.writeUTF("Hello, world");
            dos.flush();
            System.out.println("Message sent to server.");

            //Close the socket
            cs.close();
        }
        catch(UnknownHostException e) {
            System.out.println("Unable to reach the host.");
        }
        catch (IOException e) {
            System.out.println("IO Error");
        }

    }
    
}
