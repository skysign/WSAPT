package com.tistory.skysign.WSTT.AB;

public class Main {
    public static void main(String[] args) {
	    System.out.printf("%s\n", "main method");

	    AB ab = new AB();
	    String r = "";
	    r = ab.createString(3, 2);

	    System.out.printf("%s\n", r);
    }
}
