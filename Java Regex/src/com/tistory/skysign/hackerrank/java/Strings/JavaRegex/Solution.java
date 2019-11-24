package com.tistory.skysign.hackerrank.java.Strings.JavaRegex;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String IP = in.next();
            System.out.println(IP.matches(new MyRegex().pattern));
        }
        in.close();
    }
}
