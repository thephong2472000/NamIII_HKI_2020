/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBacsic;

import static JavaBacsic.Tuan01.inp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Thanh Phong
 */
public class Tuan03 {

    public  String CreatInetAddToDomain(String domain){
        try {
                    InetAddress address = InetAddress.getByName(domain);
                    return (address.getHostAddress());
                } catch (Exception e) {
                    return ("Could not find " + domain);
                }
    }
    public  void CreatInetAddToIP(String IP){
        try {
                    InetAddress address = InetAddress.getByName(IP);
                    if(address.isReachable(200)){
                        System.out.println(IP + " is OK");
                    }
                    else System.out.println(IP + " is not OK");
                        
                } catch (Exception e) {
                }
    }
    public void InDomain_OutIP() {
        while (1 == 1) {
            System.out.print("Nhập vào tên Domain: ");
            String domain = inp.nextLine();
            if (!domain.equals("exit")) {
                try {
                    InetAddress address = InetAddress.getByName(domain);
                    System.out.println(address.getHostAddress());
                } catch (Exception e) {
                    System.out.println("Could not find " + domain);
                }
            } else {
                System.out.println("Chương trình kết thúc!");
                break;
            }
        }
    }
    public void DomainToIp() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./CSDL/Domain.txt"));
            
            /*
                Scanner s = new Scanner(new File ("Đường dẫn"));
                while(s.hasMadeline()){
                s.readline();
            }
            */
            
            String domain;
            while ((domain = br.readLine()) != null) {
                System.out.println(CreatInetAddToDomain(domain));
                domain = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void IpToDomain(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./CSDL/IP.txt"));
            String IP;
            while ((IP = br.readLine()) != null) {
                CreatInetAddToIP(IP);
                IP = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Tách chuỗi rồi + bit cuối 1 -> 254
     */
    public static void main(String[] args) {
        Tuan03 tuan3 = new Tuan03();
//        tuan3.InDomain_OutIP();
//        tuan3.DomainToIp();
        tuan3.IpToDomain();
        
 
    }

}
