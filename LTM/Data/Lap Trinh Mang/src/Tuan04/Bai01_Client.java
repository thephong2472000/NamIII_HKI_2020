/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tuan04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Thanh Phong
 */
public class Bai01_Client {

    private Socket socket = null;
    BufferedWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = null;

    public Bai01_Client(String address, int port) throws UnknownHostException, IOException {
        socket = new Socket(address, port);
        System.out.println("Connected");
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        // Nhập vào str

        String line = "";
        String string = "";
        while (!line.equals("bye")) {
            System.out.print("Nhập và chuỗi: ");
            line = stdIn.readLine();
            System.out.println("Client sent: " + line);
            out.write(line);
            out.newLine();
            out.flush();
            if (!line.equals("bye")) {
                try {
                    string = in.readLine();
                    System.out.println("Client received: " + string);
                } catch (Exception i) {
                    System.err.println(i);
                }
            }
            else 
            {
                System.out.println("Closing connection");
                break; 
            }
        }
        in.close();
        out.close();
        socket.close();
    }

    public static void main(String args[]) throws UnknownHostException, IOException {
        Bai01_Client client = new Bai01_Client("127.0.0.1", 5000);
    }
}
