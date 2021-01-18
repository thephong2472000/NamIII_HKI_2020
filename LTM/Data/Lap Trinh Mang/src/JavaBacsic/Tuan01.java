/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBacsic;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Thanh Phong
 */
public class Tuan01 {

    public static Scanner inp = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    
    // -- Start câu 1
    public void TongSTN(int n) {
        System.out.println("1. S = 1 + 2 + 3 + ... + n" + " = " + n * (n + 1) / 2 + "\n");
    }
    //-- End câu 1

    // -- Start câu 2
    public void TongUoc(int n) {
        int s = 1;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                s += i;
            }
        }
        System.out.println("2. Tổng các ước nhỏ hơn hoặc bằng n là: " + s + "\n");
    } 
    // -- End câu 2

    // -- Start hàm kiểm tra nguyên tố
    public boolean SNT(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
    // End hàm kiểm tra nguyên tố

    // Start câu 3
    public void TongSNT(int n) {
        int s = 0;
        for (int i = 2; i <= n; i++) {
            if (SNT(i)) {
                //System.out.println(i);
                s += i;
            }
        }
        System.out.println("3. Tổng các số nguyên tố nhỏ hơn hoặc bằng n là : " + s + "\n");
    }
    // -- End câu 3

    // Star câu 4
    public void PTSNT(int n) {
        System.out.print("4. Phân tích thành các số nguyên tố: " + n + " = ");
        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        for (int i = 2; i <= n;) {
            if(SNT(n)){
                hashmap.put(n, 1);
                break;
            }
            if (SNT(i) && n % i == 0) {
                if (hashmap.get(i) == null) {
                    hashmap.put(i, 1);
                } else {
                    hashmap.put(i, hashmap.get(i) + 1);
                }
                n = n / i;
                if(SNT(n)){
                    hashmap.put(i, 1);
                    break;
                }
                i = i;
            } else {
                i++;
            }
        }
        for (Integer i : hashmap.keySet()) {
            System.out.print(i + "^" + hashmap.get(i) + "  ");
            for (Integer j : hashmap.values()){
                break;
            }
            
        }
    }
    // -- End câu 4

    public static void main(String[] args) {
        // TODO code application logic here
        
        // -- Start câu 5
        System.out.print("Nhập vào số n: ");
        int n = inp.nextInt();
        Tuan01 tuan1 = new Tuan01();
        tuan1.TongSTN(n);
        tuan1.TongUoc(n);
        tuan1.TongSNT(n);
        tuan1.PTSNT(n);
        // -- End câu 5
    }

}