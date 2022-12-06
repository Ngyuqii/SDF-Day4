package Network3;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client3 {
    public static void main(String[] args) {
        int port = 1212;
        try {
            Socket cs = new Socket ("localhost", 12000);

            //Get the I/O Stream
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(cs.getOutputStream()));

            //Get client input from scanner
            Scanner inputSc = new Scanner(System.in);
            String line;
            while (( line = inputSc.nextLine()) != null) {
                if (line.equalsIgnoreCase("close")) {
                    System.out.println("Exit from shell");
                    dos.writeUTF("close");
                    dos.flush();
                    break;
                }

                dos.writeUTF(line);
                dos.flush();
                System.out.println("Message sent to client >" + line);
          java  }

            //Close the socket and scanner
            cs.close();
            inputSc.close();

        } catch(UnknownHostException e) {
            System.out.println("Unable to reach the host.");
        } catch (IOException e) {
            System.out.println("IO Error");

        }
    }
    
}