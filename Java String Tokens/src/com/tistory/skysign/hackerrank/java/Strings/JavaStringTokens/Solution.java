package com.tistory.skysign.hackerrank.java.Strings.JavaStringTokens;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
//    문제를 약간 어렵게 풀어 보려고, 8개의 symbol에 대해서, string toknizer를 만들어 보았습니다.
    public static List<String> mystrtok(String S) {
        char cS[] = S.toCharArray();
        int l = S.length();
        List<String> rl = new ArrayList<String>();
        int st = 0;

        for(int i=0; i<l; ++i){
            if((cS[i] == ' ') || (cS[i] == '!') || (cS[i] == ',') || (cS[i] == '?') || (cS[i] == '.') || (cS[i] == '_') || (cS[i] == '\'') || (cS[i] == '@')) {
                if(st != i)
                    rl.add(S.substring(st, i));
                st = i+1;
            }
            else if(i == l-1) {
                rl.add(S.substring(st, i+1));
            }
        }

        return rl;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String S = scan.nextLine();
        scan.close();
        S = S.trim();

        List<String> rl = mystrtok(S);

        System.out.println(rl.size());
        for(String s : rl){
            System.out.println(s);
        }
    }

//    아래는 문제의 설명에 나와 있는 것처럼, regular expression을 사용해서 풀이하는 방법입니다.
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        String S = scan.nextLine();
//        scan.close();
//
////      Test Case 3, 6, 7을 패스 하려면, trim()을 사용해서, 스트링 좌우에 white space를 없애야 합니다.
//        S = S.trim();
////      8 개의 심볼에 따라서, regular expression을 사용해서, 스트링을 나눕니다
//        String r[] = S.split("[!,?._'@ ]+");
////      Test case 9 번에, 입력값이 '         ' 이런식으로 space만 있는 경우에 대해서,
////      0 만 output 해야합니다.
////      풀기는 했지만, 뭐 그닥 좋은 문제는 아닌 것 같아요.
//        if((r.length == 1) && (r[0].compareTo("") == 0)) {
//            System.out.println(0);
//        }
//        else {
//            System.out.println(r.length);
//            for(String s : r){
//                System.out.println(s);
//            }
//        }
//    }
}
