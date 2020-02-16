import java.util.BitSet;
import java.util.Scanner;

/**
 * Java BitSet / hackerrank.com
 * 문제링크 : https://www.hackerrank.com/challenges/java-bitset/problem
 * 제출링크 : https://www.hackerrank.com/challenges/java-bitset/submissions/code/143056213
 */
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	    int L = sc.nextInt();
        int N = sc.nextInt();
        BitSet[] bs = new BitSet[3];
        bs[1] = new BitSet(L);
        bs[2] = new BitSet(L);

        for(int i=0; i<N; ++i) {
            String cmd = sc.next();
            int a = sc.nextInt();
            int b = sc.nextInt();

            switch(cmd) {
                case "AND":
                    bs[a].and(bs[b]);
                    break;

                case "SET":
                    bs[a].set(b);
                    break;

                case "FLIP":
                    bs[a].flip(b);
                    break;

                case "OR":
                    bs[a].or(bs[b]);
                    break;

                case "XOR":
                    bs[a].xor(bs[b]);
                    break;
            }

            System.out.print(bs[1].cardinality());
            System.out.print(" ");
            System.out.println(bs[2].cardinality());
        }
    }
}
