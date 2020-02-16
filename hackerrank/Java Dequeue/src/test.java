import java.util.*;

/**
 * Java Dequeue / hackerrank.com
 * 문제링크 : https://www.hackerrank.com/challenges/java-dequeue/problem
 * 제출링크 : https://www.hackerrank.com/challenges/java-dequeue/submissions/code/143051795
 */
public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        int N = in.nextInt();
        int M = in.nextInt();
        HashSet<Integer> hs = new HashSet();

        int r =0;
        for (int i = 0; i < N; i++) {
            int a = in.nextInt();
            deque.add(a);
            hs.add(a);

            if(M == deque.size()) {
                r = Math.max(r, hs.size());
                a = deque.removeFirst();
                if(!deque.contains(a)) {
                    hs.remove(a);
                }
            }
        }
        System.out.println(r);
    }
}