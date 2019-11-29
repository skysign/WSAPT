package com.tistory.skysign.WSTT.A0Paper;

public class Main {
    public static void main(String[] args) {
        A0Paper a0Paper = new A0Paper();
//      Expected : Possible
//        int[] ps = new int[]{0, 0, 2, 4, 8};
//      Expected : Impossible
        int[] ps = new int[]{0, 0, 0, 0, 2};
        String r = "";

        r = a0Paper.canBuild(ps);
        System.out.printf("%s\n", r);
    }
}
