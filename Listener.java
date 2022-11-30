import java.util.*;
import java.io.*;
import java.net.*;

public class Listener {
    public static void main(String args[]) throws IOException {
        DatagramSocket listener = new DatagramSocket(4160);
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        listener.receive(packet);
        String response = new String(packet.getData());
        System.out.println("Response Data : " + response);
        listener.close();

    }
}