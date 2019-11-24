package com.tistory.skysign.hackerrank.java.Strings.JavaAnagrams;

import java.util.Scanner;

public class Solution {

    static String mysort(String str) {
        char[] s = str.toCharArray();
        int l = str.length();
        for(int i=0; i<l; ++i) {
            for(int j=i+1; j<l; ++j){
                if(s[i] > s[j]) {
                    char t = s[i];
                    s[i] = s[j];
                    s[j] = t;
                }
            }
        }

        return new String(s);
    }

//    아나그램인것에 아이디어를 얻어서, 스트링을 sort한 뒤에,
//    같으면, true, 다르면 false를 리턴하는 방법으로 풀 수 있습니다.
//    O(N^2) 복잡도 입니다. 좀더 빠르게 풀 수 있는 방법도 있습니다.
    static boolean isAnagram_2(String a, String b) {
        a = mysort(a.toLowerCase());
        b = mysort(b.toLowerCase());

        if(a.compareTo(b) == 0)
            return true;

        return false;
    }
//    O(N)으로 풀수 있는 방법도 있습니다.
//    a~z 까지 한자가 나올 때마다, [0~26] 에 1씩 증가시켜서, 두 배열이 같은지 확인하는 방법입니다.
static boolean isAnagram(String a, String b) {
//  a, b의 길이가 다른 경우는, 아나그램이 될 수 없습니다.
    if(a.length() != b.length())
        return false;

    a = a.toUpperCase();
    b = b.toUpperCase();

//    문제에서 스트링의 길이가 1<= N <=50 이기 때문에 char로 충분합니다.
//  자바에서는 primitive type을 new 할 때, 0으로 초기화 해줍니다.
//  초기화에 대한 자세한 내용은 -> https://stackoverflow.com/questions/3426843/what-is-the-default-initialization-of-an-array-in-java
    final int ll = 26;
    char cA[] = new char[ll];
    char cB[] = new char[ll];
    int l = a.length();
    for(int i=0; i<l; ++i) {
        cA[a.charAt(i)-'A']++;
        cB[b.charAt(i)-'A']++;
    }

    for(int i=0; i<ll; ++i) {
        if(cA[i] != cB[i])
            return false;
    }

    return true;
}

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}
