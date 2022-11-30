// accepts a string of up to fifty characters entered from the command line
// entered string must be broken up into 5 messages, ten characters each
// frame these messages with sequence numbers (1, 2, 3, 4, ...)
// talker must also create message zero (contains the number of messages to be sent)

// wait for listener to request a UDP connection
// talker then accepts the UDP connection
// talker then requests UDP connection to listener to receive ACK messages
// listener should listen for talker as soon as it issues connect request to talker

// allow ports for talker and listener to be added to command line
// allow talker's IP address to be entered from the command line to Listener

// talker should extract the Listener's IP address and use it to request a UDP connection for the ACKs. 

import java.util.*;
import java.io.*;
import java.net.*;

public class Talker {
    public static void main(String args[]) throws IOException {
        DatagramSocket talker = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        String str = "Hello World";
        byte[] buf = str.getBytes();
        DatagramPacket p = new DatagramPacket(buf, buf.length, address, 4160);
        talker.send(p);
        talker.close();
        /* 

        // print greeting asking for message to be sent
        System.out.println("Welcome. Please enter a message less than or equal to fifty characters.");
        String message = "llllllllllllllllllllllllllllllllllllllllllllllllllll";
        while (message.length() > 50) {
            Scanner in = new Scanner(System.in);
            message = in.nextLine();
            System.out.println("You entered string " + message);
            if (message.length() > 50) {
                System.out.println("Your message is greater than 50 characters. Please enter a valid message.");
            }
        }
        // break message up into 5 messages of 10 characters each
        System.out.println("Your message length is " + message.length());
        double number_of_messages = Math.ceil(message.length() / 10);
        System.out.println("The number of messages is " + number_of_messages);
        String message0 = 
        String message1 = 
        String message2 = 
        String message3 = 
        String message4 = 
        String message5 = 
        */
    }
}
