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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Thanh Phong
 */
public class Bai01_Server {
    private Socket socket = null;
    private ServerSocket server = null; 
    BufferedWriter out = null;
    BufferedReader in = null;
  
    public Bai01_Server(int port) 
    { 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
            System.out.println("Waiting for a client ..."); 
            socket = server.accept(); 
            System.out.println("Client accepted"); 
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = ""; 
            // # Nhận và xuất ra str
            while (!line.equals("bye")) 
            { 
                try
                { 
                    // # Nhận về
                    line = in.readLine();
                    System.out.println("Server received: " + line);
                    // Đảo ngược
                    StringBuilder str = new StringBuilder(line);
                    str = str.reverse();
                    //System.out.println(str);
                    
                    // # Gửi đi
                    out.write(str.toString());
                    out.newLine();
                    out.flush();
                } 
                catch(IOException i) 
                { 
                    System.err.println(i); 
                } 
            } 
            System.out.println("Closing connection"); 
            
            in.close(); 
            out.close();
            socket.close(); 
            server.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    }
    
    public static void main(String args[]) 
    { 
        Bai01_Server server = new Bai01_Server(5000); 
    }
}
