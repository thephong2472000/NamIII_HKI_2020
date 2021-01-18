/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBacsic;

import static JavaBacsic.Tuan01.inp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;

/**
 *
 * @author Thanh Phong
 */
public class Tuan02 {

    HashMap<String, String> _av = new HashMap<String, String>();
    StringTokenizer token;

    public void DocFile(String namefile) {
        File text = new File(namefile);

        Scanner scanner = null;
        try {
            scanner = new Scanner(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Tách ra và gắn vào HashMap
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            token = new StringTokenizer(line, "  ", false);
            while (token.hasMoreTokens()) {
                String tmp = token.nextToken();
                if (_av.get(tmp) != null) {
                    _av.put(tmp, token.nextToken());
                   // System.out.println(_av.get("﻿Hello"));
                }

            }
            /////System.out.println(line);
        }
//        // Xuất token ra
//        for (String i : _av.keySet()) {
//            System.out.print(_av.keySet() + "  ");
//
//        }
    }

    public void Calculator() {

        // -- Start câu 1
        // Kiểm tra số về nhà hoàn thiện sau
        // StringTokenizer
        System.out.print("Nhập vào chuỗi: ");
        String string = inp.nextLine();
        StringTokenizer token = new StringTokenizer(string, "+-*/", true);
        int a = Integer.parseInt(token.nextToken());
        String cal = token.nextToken();
        int b = Integer.parseInt(token.nextToken());
        switch (cal) {
            case "+": {
                System.out.println("Kết quả là: " + (a + b));
                break;
            }
            case "-": {
                System.out.println("Kết quả là: " + (a - b));
                break;
            }
            case "*": {
                System.out.println("Kết quả là: " + (a * b));
                break;
            }
            case "/": {
                if (b == 0) {
                    System.out.println("Không chia được, b = 0!");
                    break;
                } else {
                    System.out.println("Kết quả là: " + (double) (a + b));
                    break;
                }

            }
        }
    }

    // -- End câu 1
    // Start câu 2
    public void XuLyChuoi() {
        // StringTokenizer   và    HashMap  || LinkedHashMap
        System.out.print("Nhập vào chuỗi: ");
        String in = inp.nextLine();
        StringTokenizer inputotken = new StringTokenizer(in, " ");
        HashMap<String, String> hashmap = new HashMap<String, String>();
        while (inputotken.hasMoreTokens()) {
            String tmp = inputotken.nextToken();
            if (hashmap.get(tmp) == null) {
                hashmap.put(tmp, tmp);
                System.out.print(tmp + " ");
            }
        }
    }

    // End câu 2
    // Start câu 3
    public void Language() {
        DocFile("./CSDL/Language.txt");
        System.out.print("Nhập vào chuỗi:");
        String in = inp.nextLine();
//        System.out.println(_av.get(in));
//        if(_av.get(in) == null)
//        {
//            System.out.println(_av.get(in));
//        }
//        else System.out.println("Không tìm thấy!");

    }

    // End câu 3
    public static void main(String[] args) {
        Tuan02 tuan2 = new Tuan02();
        //tuan2.Calculator();
        tuan2.XuLyChuoi();
        //tuan2.Language();
    }
}
