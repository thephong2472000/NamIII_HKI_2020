/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tuan05;

import ClientServer.Server_Demo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Thanh Phong
 */
public class Bai01_Server {
    private Socket socket = null;
    private ServerSocket server = null; 
    BufferedWriter out = null;
    BufferedReader in = null;
    
    public void DocFile(String namefile){
            File text = new File(namefile);

		Scanner scanner = null;
		try {
			scanner = new Scanner(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// đọc file theo dòng
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
		}
        }
  
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
            while (!line.equals("Bye")) 
            { 
                try
                { 
                    line = in.readLine();
                    System.out.println("Server received: " + line);
                    DocFile(".\\src\\Tuan05\\Dictionary.txt");
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
