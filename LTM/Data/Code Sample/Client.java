// A Java program for a Client
//import java.nio.charset.StandardCharsets;
import java.net.*;
import java.io.*; 

public class Client 
{ 
	private Socket socket = null; 
	BufferedWriter out = null;
	BufferedReader in = null;
	BufferedReader stdIn = null; 

	public Client(String address, int port) throws UnknownHostException, IOException
	{ 
		socket = new Socket(address, port); 
		System.out.println("Connected"); 
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		stdIn = new BufferedReader(new InputStreamReader(System.in));

		String line = ""; 
		while (!line.equals("Over")) 
		{ 
			line = stdIn.readLine();
			System.out.println("Client sent: " + line);
			out.write(line);
			out.newLine();
			out.flush();
		} 
		in.close(); 
		out.close(); 
		socket.close(); 
	} 

	public static void main(String args[]) throws UnknownHostException, IOException 
	{ 
		Client client = new Client("127.0.0.1", 5000); 
	} 
} 