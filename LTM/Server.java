// A Java program for a Server
//import java.nio.charset.StandardCharsets;
import java.net.*;
import java.io.*; 
  
public class Server 
{ 
    private Socket socket = null;
    private ServerSocket server = null; 
    BufferedWriter out = null;
    BufferedReader in = null;
  
    public Server(int port) 
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
        Server server = new Server(5000); 
    } 
} 