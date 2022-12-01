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
        if (message.length() % 10 != 0) {
            int fill = 10 - (message.length() % 10);
            for (int i = 0; i < fill; i++) {
                message = message + '\0';
            }
        }
        
        //parse the string into 10 char messages
        String[] msgs = {"", "", "", "", "", ""}; //the array of msgs has 6 spots since msg 0 will tell Listener how many msgs
        int number_of_messages = 0; 
        int message_length = message.length();
        while (message_length > 0) {
            message_length = message_length - 10;
            number_of_messages += 1;
        }
        //set first message to a character holding the number of msgs
        String numMsg = String.valueOf(number_of_messages);
        msgs[0] = numMsg;
        
        int index = 0;
        for (int i = 1; i <= number_of_messages; i++) {
            msgs[i] = message.substring(index, index + 10);
            msgs[i] = i + msgs[i]; //add a tag to each msg identifying which msg # it is
            index += 10;
            
        }
        //TEST displays messages with their framing number
        for (int i = 0; i <= number_of_messages; i++) {
            System.out.println(msgs[i]);
        }

        System.out.println("Enter the port of the Listener.");
        Scanner in = new Scanner(System.in);
        String listener_port_string = in.nextLine();
        int listener_port = Integer.parseInt(listener_port_string);
        in.close();
        System.out.println("The listener port is " + listener_port);
        DatagramSocket talker = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");



        byte[] buf = message.getBytes();
        DatagramPacket p = new DatagramPacket(buf, buf.length, address, listener_port);
        talker.send(p);

        
        byte[] buf1 = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf1, buf1.length);
        talker.receive(packet);
        String response = new String(packet.getData());
        System.out.println("Acknowledged : " + response);


        talker.close();
        /* 
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
