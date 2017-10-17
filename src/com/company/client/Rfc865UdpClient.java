package com.company.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * @name Michael Jonathan
 * @group SEP1
 * @IP Address 172.21.146.118
 */
public class Rfc865UdpClient {
    private static DatagramSocket socket;
    public static void main(String[] args) {
        try {
            socket = new DatagramSocket(8000);
            try {
                String requestString = "Michael Jonathan, SEP1, "
                        + "172.21.146.118";
                byte[] requestBuffer = requestString.getBytes();
                InetAddress address = InetAddress.getByName("hw1-a00");
                DatagramPacket request = new DatagramPacket(requestBuffer, 
                    requestBuffer.length, address, 17);
                
                System.out.println("Sending request datagram");
                socket.send(request);
                System.out.println("Datagram sent");    
                
                byte[] replyBuffer = new byte[512];
                DatagramPacket reply = new DatagramPacket(replyBuffer, 
                        replyBuffer.length);
                
                System.out.println("Receiving quote datagram");
                socket.receive(reply);
                System.out.println("Quote datagram received");
                
                String quote = new String(reply.getData(), 0, 
                        reply.getLength());
                System.out.println("Quote of the day: " + quote);  
                
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }   
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
