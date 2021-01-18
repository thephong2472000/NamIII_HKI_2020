/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Thanh Phong
 */
public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    BufferedWriter out = null;
    BufferedReader in = null;
    static HashMap<String, String> hashmap = new HashMap<String, String>();

    // -- Đọc file - tách từ - gắn vào hashmap
    public void DocFile(String namefile) throws IOException {
        try (FileReader fileReader = new FileReader(namefile); Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] string = line.split(";", 0);
                hashmap.put(string[0], string[1]); // - Tiếng Anh -> Tiếng Việt
                hashmap.put(string[1], string[0]); // - Tiếng Việt -> Tiếng Anh
            }
        }
    }

    // Ghi từ hashmap ra file
    public void GhiFile(String namefile) throws IOException {

        try (FileWriter fileWriter = new FileWriter(namefile)) {
            // -- Bắt đầu ghi
            for (String i : hashmap.keySet()) {
                fileWriter.write(i + ";" + hashmap.get(i) + "\n");
            }
        }
    }

    private String ADD(String[] string) {
        // TODO Auto-generated method stub
        if (!string[1].equals("") && !string[2].equals("")) {
            if (hashmap.get(string[1]) != null) {
                return "Từ đã tồn tại!";
            } else {
                hashmap.put(string[1], string[2]);
                return "Thêm thành công!";
            }
        } else {
            return "Sai cú pháp!";
        }

    }

    private String DEL(String[] string) {
        if (!string[1].equals("")) {
            if (hashmap.get(string[1]) == null) {
                return "Từ này không có trong từ điển!";
            } else {
                hashmap.remove(string[1]);
                return "Xóa thành công!";
            }
        } else {
            return "Sai cú pháp!";
        }
    }

    // -- Hàm xữ lý
    public Server(int port) throws IOException {
        String namefile = ".\\\\src\\\\BT01\\\\Dictionary.txt";
        DocFile(namefile);
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = "";
            String sent = "";
            while (!line.equals("Bye")) {
                try {
                    line = in.readLine();
                    System.out.println("Server received: " + line);
                    String[] string = line.split(";", 0);
                    if (string[0].equals("ADD")) {
                        sent = ADD(string);
                    } else if (string[0].equals("DEL")) {
                        sent = DEL(string);
                    } else {
                        if (hashmap.get(line) != null) {
                            sent = hashmap.get(line).toString();
                            System.out.println("Server sent: " + string);
                        } else {
                            sent = "Không tìm thấy!";
                            System.out.println("Server sent: Không tìm thấy!");
                        }
                    }
                } catch (IOException i) {
                    System.err.println(i);
                }
                out.write(sent);
                out.newLine();
                out.flush();
            }
            System.out.println("Closing connection");
            in.close();
            out.close();
            socket.close();
            server.close();
            GhiFile(namefile);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    // -- Main
    public static void main(String[] args) throws IOException {
        Server server = new Server(5000);
    }
}
