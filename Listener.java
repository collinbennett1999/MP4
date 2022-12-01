import java.util.*;
import java.io.*;
import java.net.*;

public class Listener {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        // get the port of the talker :: port example number: 4160
        System.out.println("Enter the port of the Talker.");
        Scanner in = new Scanner(System.in);
        int talker_port = Integer.parseInt(in.nextLine());
        System.out.println("The talker port is " + talker_port);

        // get the ip address of the talker
        System.out.println("Enter the IP address of the Talker."); 
        String talker_IP_address = in.nextLine();
        System.out.println("The talker IP address is " + talker_IP_address);
        in.close();

        // Request a UDP connection with the Talker 
        DatagramSocket listener = new DatagramSocket(talker_port);
///////////////////////////////////////////////////////////////////////////////////////


        //InetAddress address = InetAddress.getByName("localhost");
        //listener.connect(address, talker_port);
        //System.out.println("Now connected to the talker.");
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        listener.receive(packet);
        //Listener checks port to send acknoledgement to
        int ackport = packet.getPort();
        String response = new String(packet.getData());
        System.out.println("Response Data : " + response);

        //Listener sends back acknowledgement 
        //to do: Send incrementing acknoledgements corresponding to message # sent
        InetAddress address = InetAddress.getByName("localhost");
        String ackString = "Message Received";
        byte[] buf1 = ackString.getBytes();
        DatagramPacket p = new DatagramPacket(buf1, buf1.length, address, ackport);
        listener.send(p);
        System.out.println("Sent ack");
        listener.close();

    }
}