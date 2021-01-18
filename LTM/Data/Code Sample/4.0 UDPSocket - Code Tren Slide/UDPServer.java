package v4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
	public static int buffsize = 512;
	public static int port = 1234;
	public static void main(String[] args) {
		DatagramSocket socket;
		DatagramPacket dpreceive, dpsend;
		try {
			socket = new DatagramSocket(1234);
			dpreceive = new DatagramPacket(new byte[buffsize], buffsize);
			while(true) {
				socket.receive(dpreceive);
				String tmp = new String(dpreceive.getData(), 0 , dpreceive.getLength());
				System.out.println("Server received: " + tmp + " from " + 
						dpreceive.getAddress().getHostAddress() + " at port " + 
						socket.getLocalPort());
				if(tmp.equals("bye")) {
					System.out.println("Server socket closed");
					socket.close();
					break;
				}
				// Uppercase, sent back to client
				tmp = tmp.toUpperCase();
				dpsend = new DatagramPacket(tmp.getBytes(), tmp.getBytes().length, 
						dpreceive.getAddress(), dpreceive.getPort());
				System.out.println("Server sent back " + tmp + " to client");
				socket.send(dpsend);
			}
		} catch (IOException e) { System.err.println(e);}
	}

}
