package com.company.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;

public class Rfc865UdpClient {

    private static DatagramSocket socket;

    public static void main(String[] args) {

        try {
            socket = new DatagramSocket(9000);

            while(true) {
                try {
                    byte[] requestBuffer = new byte[512];
                    InetAddress address = InetAddress.getLocalHost();
                    DatagramPacket request = new DatagramPacket(requestBuffer, requestBuffer.length, address, 8000);
                    System.out.println("Sending request datagram");
                    socket.send(request);

                    byte[] replyBuffer = new byte[512];
                    DatagramPacket reply = new DatagramPacket(replyBuffer, replyBuffer.length);
                    System.out.println("Receiving quote datagram");
                    socket.receive(reply);
                    String quote = new String(reply.getData(), 0, reply.getLength());
                    System.out.println(quote.length());
                    System.out.println("Quote of the day: " + quote);

                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return;

    }

}
