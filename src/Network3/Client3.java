package network3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client3 {

    public static void main(String[] args) {

        try {
            Socket skt = new Socket ("localhost", 12000);

            //Output stream
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(skt.getOutputStream()));

            //Read a file and write to server
            FileReader fr = new FileReader("src/network3/file.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while (null != (line = br.readLine())) {
                dos.writeUTF(line);
                dos.flush();
            }

            fr.close(); //close the file
            skt.close(); //close the socket 
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}