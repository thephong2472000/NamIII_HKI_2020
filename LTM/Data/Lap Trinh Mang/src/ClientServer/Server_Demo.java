/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServer;

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
public class Server_Demo {
    private Socket socket = null;
    private ServerSocket server = null; 
    BufferedWriter out = null;
    BufferedReader in = null;
  
    public Server_Demo(int port) 
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
            while (!line.equals("Over")) 
            { 
                try
                { 
                    line = in.readLine();
                    System.out.println("Server received: " + line); 
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
        Server_Demo server = new Server_Demo(5000); 
    }
    
}
