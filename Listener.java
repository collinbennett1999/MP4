import java.util.*;
import java.io.*;
import java.net.*;

public class Listener {
    public static void main(String args[]) throws IOException {
        System.out.println("Enter the port of the Talker.");
        Scanner in = new Scanner(System.in);
        int talker_port = Integer.parseInt(in.nextLine());
        System.out.println("The talker port is " + talker_port);

        System.out.println("Enter the IP address of the Talker.");
        in = new Scanner(System.in);
        String talker_IP_address = in.nextLine();
        in.close();
        System.out.println("The talker IP address is " + talker_IP_address);

        // port example number: 4160
        DatagramSocket listener = new DatagramSocket(talker_port);
        //InetAddress address = InetAddress.getByName("localhost");
        //listener.connect(address, talker_port);
        //System.out.println("Now connected to the talker.");
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        listener.receive(packet);
        String response = new String(packet.getData());
        System.out.println("Response Data : " + response);
        listener.close();

    }
}