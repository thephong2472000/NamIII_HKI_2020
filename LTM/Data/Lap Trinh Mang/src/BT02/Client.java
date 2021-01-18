/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Thanh Phong
 */
public class Client {

    public static int destPort = 1234;
    public static String hostname = "localhost";

    public static void main(String[] args) {
        DatagramSocket socket;
        DatagramPacket dpsend, dpreceive;
        InetAddress add;
        Scanner stdIn;
        try {
            System.out.println("Client start!");
            add = InetAddress.getByName(hostname);	//UnknownHostException
            socket = new DatagramSocket();			//SocketException
            stdIn = new Scanner(System.in);
            while (true) {
                System.out.print("Client input: ");
                String tmp = stdIn.nextLine();
                byte[] data = tmp.getBytes();
                dpsend = new DatagramPacket(data, data.length, add, destPort);
                socket.send(dpsend);				//IOExeption
                if (tmp.equals("bye")) {
                    System.out.println("Client socket closed");
                    stdIn.close();
                    socket.close();
                    break;
                }
                // Get response from server
                dpreceive = new DatagramPacket(new byte[512], 512);
                while (true) {
                    socket.receive(dpreceive);
                    tmp = new String(dpreceive.getData(), 0, dpreceive.getLength());
                    if (!tmp.equals("End")) {
                        System.out.println(tmp);
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
