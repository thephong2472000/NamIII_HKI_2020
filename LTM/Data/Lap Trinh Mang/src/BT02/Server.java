/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT02;

/**
 *
 * @author Thanh Phong
 */
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.json.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Server {

    public static int buffsize = 512;
    public static int port = 1234;
    private static ArrayList<String> country;
    private static String[] keyword;
    private static String stringJson;

    public static ArrayList<String> XuLyHello(String urls) {
        try {
            country = new ArrayList<String>();

//            https://api.airvisual.com/v2/countries?&key=b439f4a2-c54d-4239-a891-f25ee04d99e4 
//            String key = "b439f4a2-c54d-4239-a891-f25ee04d99e4";
//            String url = "https://api.airvisual.com/v2/countries?&key=" + key;
            // -- Một cách connect khác
            //Document pod = Jsoup.connect(urlLogin).ignoreContentType(true).get();
            //System.out.println(pod);
            // -- Connect vào url
            Connection.Response response = Jsoup.connect(urls)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.GET)
                    .execute();

            // -- Parse dữ liệu ra json
            stringJson = response.parse().body().text().toString();
            JSONObject objectJson = new JSONObject(stringJson); // -- Json sang Object
            String status = objectJson.getString("status"); // -- Lấy status .toString
            JSONArray data = objectJson.getJSONArray("data"); // -- Lấy data và .toArray
            if (status.equals("fail")) {
                ArrayList<String> ketqua = new ArrayList<>();
                ketqua.add("fail");
                ketqua.add("Sai cú pháp!");
                return ketqua;
            }
            country.add(status);
            // Tách data và gắn vào string[]
            for (int i = 0; i <= data.length(); i++) {
                //System.out.println(i+1 + ". "+(data.getJSONObject(i).getString("country"))); -- Xuất nó ra
                country.add((i + 1) + ". " + data.getJSONObject(i).getString("country")); // -- Add vào string[i]
            }
        } catch (Exception e) {
        }
        return country;
    }

    public static ArrayList<String> XuLyState(String urls) {
        try {
            country = new ArrayList<String>();
            Connection.Response response = Jsoup.connect(urls)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.GET)
                    .execute();

            // -- Parse dữ liệu ra json
            stringJson = response.parse().body().text().toString();
            JSONObject objectJson = new JSONObject(stringJson); // -- Json sang Object
            String status = objectJson.getString("status"); // -- Lấy status .toString
            JSONArray data = objectJson.getJSONArray("data"); // -- Lấy data và .toArray
            if (status.equals("fail")) {
                ArrayList<String> ketqua = new ArrayList<>();
                ketqua.add("fail");
                ketqua.add("Sai cú pháp!");
                return ketqua;
            }
            country.add(status);
            // Tách data và gắn vào string[]
            for (int i = 0; i <= data.length(); i++) {
                //System.out.println(i+1 + ". "+(data.getJSONObject(i).getString("country"))); -- Xuất nó ra
                country.add((i + 1) + ". " + data.getJSONObject(i).getString("state")); // -- Add vào string[i]
            }
        } catch (Exception e) {
        }
        return country;
    }

    public static ArrayList<String> XuLyCity(String urls) {
        try {
            country = new ArrayList<String>();
            Connection.Response response = Jsoup.connect(urls)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.GET)
                    .execute();

            // -- Parse dữ liệu ra json
            stringJson = response.parse().body().text().toString();
            JSONObject objectJson = new JSONObject(stringJson); // -- Json sang Object
            String status = objectJson.getString("status"); // -- Lấy status .toString
            JSONArray data = objectJson.getJSONArray("data"); // -- Lấy data và .toArray
            if (status.equals("fail")) {
                ArrayList<String> ketqua = new ArrayList<>();
                ketqua.add("fail");
                ketqua.add("Sai cú pháp!");
                return ketqua;
            }
            country.add(status);
            // Tách data và gắn vào string[]
            for (int i = 0; i <= data.length(); i++) {
                //System.out.println(i+1 + ". "+(data.getJSONObject(i).getString("country"))); -- Xuất nó ra
                country.add((i + 1) + ". " + data.getJSONObject(i).getString("city")); // -- Add vào string[i]
            }
        } catch (Exception e) {
        }
        return country;
    }
    
    public static ArrayList<String> XuLyCLKK(String urls) {
        try {
            country = new ArrayList<String>();
            Connection.Response response = Jsoup.connect(urls)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.GET)
                    .execute();

            // -- Parse dữ liệu ra json
            stringJson = response.parse().body().text().toString();
            JSONObject objectJson = new JSONObject(stringJson); // -- Json sang Object
            String status = objectJson.getString("status"); // -- Lấy status .toString
            JSONArray data = objectJson.getJSONArray("data"); // -- Lấy data và .toArray
            if (status.equals("fail")) {
                ArrayList<String> ketqua = new ArrayList<>();
                ketqua.add("fail");
                ketqua.add("Sai cú pháp!");
                return ketqua;
            }
            country.add(status);
            // Tách data và gắn vào string[]
            for (int i = 0; i <= data.length(); i++) {
                //System.out.println(i+1 + ". "+(data.getJSONObject(i).getString("country"))); -- Xuất nó ra
                country.add((i + 1) + ". " + data.getJSONObject(i).getJSONObject("current").getJSONObject("pollution").getString("aqius")); // -- Add vào string[i]
            }
        } catch (Exception e) {
        }
        return country;
    }
    // -- Hàm main
    public static void main(String[] args) throws IOException, JSONException {
        DatagramSocket socket;
        DatagramPacket dpreceive, dpsend;

        try {

            System.out.println("Server start!");
            socket = new DatagramSocket(1234);
            dpreceive = new DatagramPacket(new byte[buffsize], buffsize);
            while (true) {
                // https://api.airvisual.com/v2/countries?&key=b439f4a2-c54d-4239-a891-f25ee04d99e4 
                String key = "b439f4a2-c54d-4239-a891-f25ee04d99e4";
                String url = "https://api.airvisual.com/v2/";
                socket.receive(dpreceive);
                String tmp = new String(dpreceive.getData(), 0, dpreceive.getLength());
                System.out.println("Server received: " + tmp + " from "
                        + dpreceive.getAddress().getHostAddress() + " at port "
                        + socket.getLocalPort());
                // -- Trường hợp tắt Server
                if (tmp.equals("bye")) {
                    System.out.println("Server socket closed");
                    socket.close();
                    break;
                }

                // -- Xét Input
                keyword = tmp.split(";", 0);

                // API, sent back to client
                if (tmp.equals("Hello")) {
                    url = url + "countries?&key=" + key;
                    ArrayList<String> tmp1 = XuLyHello(url);
                    try {
                        for (int i = 1; i <= tmp1.size(); i++) {
                            //System.out.println(string[i]);
                            tmp = tmp1.get(i);
                            dpsend = new DatagramPacket(tmp.getBytes(), tmp.getBytes().length,
                                    dpreceive.getAddress(), dpreceive.getPort());
                            System.out.println("Server sent back " + tmp + " to client");
                            socket.send(dpsend);
                        }
                    } catch (Exception e) {
                        tmp = "End";
                        dpsend = new DatagramPacket(tmp.getBytes(), tmp.getBytes().length,
                                dpreceive.getAddress(), dpreceive.getPort());
                        System.out.println("Server sent back " + tmp + " to client");
                        socket.send(dpsend);
                    }

                } else {
                    switch (keyword.length) {
                        case 1:
                            url = url + "states?country=" + keyword[0] + "&key=" + key;
                            ArrayList<String> tmp1 = XuLyState(url);
                            try {
                                for (int i = 1; i <= tmp1.size(); i++) {
                                    //System.out.println(string[i]);
                                    tmp = tmp1.get(i);
                                    dpsend = new DatagramPacket(tmp.getBytes(), tmp.getBytes().length,
                                            dpreceive.getAddress(), dpreceive.getPort());
                                    System.out.println("Server sent back " + tmp + " to client");
                                    socket.send(dpsend);
                                }
                            } catch (Exception e) {
                                tmp = "End";
                                dpsend = new DatagramPacket(tmp.getBytes(), tmp.getBytes().length,
                                        dpreceive.getAddress(), dpreceive.getPort());
                                System.out.println("Server sent back " + tmp + " to client");
                                socket.send(dpsend);
                            }
                            break;
                        case 2:
                            url = url + "cities?state=" + keyword[1] + "&country="
                                    + keyword[0] + "&key=" + key;
                                tmp1 = XuLyCity(url);
                            try {
                                for (int i = 1; i <= tmp1.size(); i++) {
                                    //System.out.println(string[i]);
                                    tmp = tmp1.get(i);
                                    dpsend = new DatagramPacket(tmp.getBytes(), tmp.getBytes().length,
                                            dpreceive.getAddress(), dpreceive.getPort());
                                    System.out.println("Server sent back " + tmp + " to client");
                                    socket.send(dpsend);
                                }
                            } catch (Exception e) {
                                tmp = "End";
                                dpsend = new DatagramPacket(tmp.getBytes(), tmp.getBytes().length,
                                        dpreceive.getAddress(), dpreceive.getPort());
                                System.out.println("Server sent back " + tmp + " to client");
                                socket.send(dpsend);
                            }
                            break;
                        case 3:
                            url = url + "city?city=" + keyword[2] + "&state=" + keyword[1] + "&country="
                                    + keyword[0] + "&key=" + key;
                             tmp1 = XuLyCLKK(url);
                            try {
                                for (int i = 1; i <= tmp1.size(); i++) {
                                    //System.out.println(string[i]);
                                    tmp = tmp1.get(i);
                                    dpsend = new DatagramPacket(tmp.getBytes(), tmp.getBytes().length,
                                            dpreceive.getAddress(), dpreceive.getPort());
                                    System.out.println("Server sent back " + tmp + " to client");
                                    socket.send(dpsend);
                                }
                            } catch (Exception e) {
                                tmp = "End";
                                dpsend = new DatagramPacket(tmp.getBytes(), tmp.getBytes().length,
                                        dpreceive.getAddress(), dpreceive.getPort());
                                System.out.println("Server sent back " + tmp + " to client");
                                socket.send(dpsend);
                            }
                            break;
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace(); // -- In ra lỗi ngay dòng nào
        }
    }
}
