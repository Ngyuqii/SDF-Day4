package network2;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client2 {
    
    public static void main(String[] args) {

        int port = 9999;

        try {
            Socket cs = new Socket ("localhost", port);

            //Get the I/O Stream
            OutputStream os = cs.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            //Get client input from scanner
            Scanner inputSc = new Scanner(System.in);

            String line;
            while ((line = inputSc.nextLine()) != null) {
                if (line.equalsIgnoreCase("close")) {
                    System.out.println("Exit from shell");
                    dos.writeUTF("Closing....");
                    dos.flush();
                }
                else {
                    dos.writeUTF(line);
                    dos.flush();
                    System.out.println("Message sent to client >" + line);
                }
            }

            //Close the socket and scanner
            inputSc.close();
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