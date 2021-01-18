package v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server1 {
	private static ServerSocket server = null;
	private static Socket socket = null;
	private static BufferedReader in = null;
	private static BufferedWriter out = null;
	
	public static void main(String[] args) {
		try {
			server = new ServerSocket(5000);
			System.out.println("Server started...");
			socket = server.accept();
			System.out.println("Client connected...");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while(true) {
				// Server nhận dữ liệu từ client qua stream
				String line = in.readLine();
				if (line.equals("bye"))
					break;
				System.out.println("Server get: " + line);
				// Server gửi phản hồi ngược lại cho client (chuỗi đảo ngược)
				StringBuilder newline = new StringBuilder();
				newline.append(line);
				line = newline.reverse().toString();
				out.write(line + '\n');
				out.newLine();
				out.flush();
			}
			System.out.println("Server closed connection");
			in.close();
			out.close();
			socket.close();
			server.close();
			
		} catch (IOException e) { System.err.println(e); }
	}

}
