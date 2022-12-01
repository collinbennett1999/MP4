import java.util.*;
import java.io.*;
import java.net.*;

public class Talker {
    public static void main(String args[]) throws IOException {
        // get the string to be sent
        String string = "llllllllllllllllllllllllllllllllllllllllllllllllllll";
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome. Please enter a string less than or equal to fifty characters.");
        while (string.length() > 50) {
            string = in.nextLine(); 
            System.out.println("You entered string " + string);
            if (string.length() > 50) {
                System.out.println("Your string is greater than 50 characters. Please enter a valid string.");
            }
        }

        // fill empty message spots with null characters 
        if (string.length() % 10 != 0) {
            int fill = 10 - (string.length() % 10);
            for (int i = 0; i < fill; i++) {
                string = string + '\0';
            }
        }
        
        // at most the string can be parsed into 5 messages of 10 chars each
        String[] msgs = {"", "", "", "", "", ""}; // the array has 6 msgs since msg 0 will tell Listener # of msgs
        int number_of_messages = 0; 
        int message_length = string.length();
        while (message_length > 0) {
            message_length = message_length - 10;
            number_of_messages += 1;
        }

        //set first message to a character holding the number of msgs
        String numMsg = String.valueOf(number_of_messages);
        msgs[0] = numMsg;
        
        int index = 0;
        for (int i = 1; i <= number_of_messages; i++) {
            msgs[i] = string.substring(index, index + 10);
            msgs[i] = i + msgs[i]; //add a tag to each msg identifying which msg # it is
            index += 10;
        }

        //TEST displays messages with their framing number
        for (int i = 0; i <= number_of_messages; i++) {
            System.out.println(msgs[i]);
        }

        System.out.println("Enter the port of the Listener.");
        // Scanner in = new Scanner(System.in);
        String listener_port_string = in.nextLine();
        int listener_port = Integer.parseInt(listener_port_string);
        System.out.println("The listener port is " + listener_port);
        in.close();
        DatagramSocket talker = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");

        // convert string to bytes
        byte[] buf0 = msgs[0].getBytes();
        DatagramPacket p0 = new DatagramPacket(buf0, buf0.length, address, listener_port);
        talker.send(p0);

        byte[] rec1 = new byte[256];
        DatagramPacket packet = new DatagramPacket(rec1, rec1.length);
        talker.receive(packet);
        String response = new String(packet.getData());
        System.out.println("Acknowledged : " + response);

    
        byte[] buf1 = msgs[1].getBytes();
        DatagramPacket p1 = new DatagramPacket(buf1, buf1.length, address, listener_port);
        talker.send(p1);
        
        byte[] buf2 = msgs[2].getBytes();
        DatagramPacket p2 = new DatagramPacket(buf2, buf2.length, address, listener_port);
        talker.send(p2);
        
        byte[] buf3 = msgs[3].getBytes();
        DatagramPacket p3 = new DatagramPacket(buf3, buf3.length, address, listener_port);
        talker.send(p3);
        
        byte[] buf4 = msgs[4].getBytes();
        DatagramPacket p4 = new DatagramPacket(buf4, buf4.length, address, listener_port);
        talker.send(p4);
        
        byte[] buf5 = msgs[5].getBytes();
        DatagramPacket p5 = new DatagramPacket(buf5, buf5.length, address, listener_port);
        talker.send(p5);
        
        

        talker.close();
    }
}
