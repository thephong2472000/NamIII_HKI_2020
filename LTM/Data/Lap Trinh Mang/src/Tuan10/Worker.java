package Tuan10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Worker implements Runnable {

    private String myName;
    private Socket socket;
    private String send = "";
    BufferedReader in;
    BufferedWriter out;

    public Worker(Socket s, String name) throws IOException {
        this.socket = s;
        this.myName = name;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    public void run() {
        System.out.println("Client " + socket.toString() + " accepted");
        try {
            String input = "";
            while (true) {
                input = in.readLine();
                // Start

                String[] messenger = input.split("#", 0);

                //System.out.println("Server received: " + messenger[1] + " from " + socket.toString() + " # Client " + myName);
                switch (messenger[0].toString()) {
                    case "1": {
                        send = "1";
                        for (Worker worker : Server.workers) {
                            if (worker.myName.equals(send)) {
                                worker.out.write(messenger[1] + '\n');
                                worker.out.flush();
                                System.out.println("Server write: " + messenger[1] + " to " + send);
                                break;
                            }
                        }
                        break;
                    }
                    case "2": {
                        send = "2";
                        for (Worker worker : Server.workers) {
                            if (worker.myName.equals(send)) {
                                worker.out.write(messenger[1] + '\n');
                                worker.out.flush();
                                System.out.println("Server write: " + messenger[1] + " to " + send);
                                break;
                            }
                        }
                        break;
                    }
                    case "3": {
                        send = "3";
                        for (Worker worker : Server.workers) {
                            if (worker.myName.equals(send)) {
                                worker.out.write(messenger[1] + '\n');
                                worker.out.flush();
                                System.out.println("Server write: " + messenger[1] + " to " + send);
                                break;
                            }
                        }
                        break;
                    }
                    case "all": {
                        send = "all";
                        for (Worker worker : Server.workers) {
                            if (!myName.equals(worker.myName)) {
                                worker.out.write(messenger[1] + '\n');
                                worker.out.flush();
                                System.out.println("Server write: " + messenger[1] + " to " + send);
                            }

                        }
                        break;
                    }
                }

                // End
                if (input.equals("bye")) {
                    String tmp = "Offf";
                    for (Worker worker : Server.workers) {
                            if (!myName.equals(worker.myName)) {
                                worker.out.write(tmp + '\n');
                                worker.out.flush();
                                System.out.println("Server write: " + messenger[1] + " to " + send);
                            }

                        }
                    break;
                }
            }
            System.out.println("Closed socket for client " + myName + " " + socket.toString());
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
