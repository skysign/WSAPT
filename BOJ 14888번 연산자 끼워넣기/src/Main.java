import java.io.*;
import java.util.ArrayList;

/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 14888번 연산자 끼워넣기
 *
 * 유튜브 문제 풀이: https://youtu.be/uNvcqpVEQXY
 *
 * 문제링크: https://www.acmicpc.net/problem/14888
 *
 * 자바소스: https://bit.ly/3rLqmt8
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[] An;
    ArrayList<Character> al;
    ArrayList<Character> ops;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    String[] strs;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        An = new int[N];
        al = new ArrayList<>();
        ops = new ArrayList<>();


        strs = br.readLine().split(" ");

        for (int i = 0; i < strs.length; ++i) {
            An[i] = Integer.parseInt(strs[i]);
        }

        strs = br.readLine().split(" ");

        for (int i = 0; i < strs.length; ++i) {
            int j = Integer.parseInt(strs[i]);
            for (int k = 0; k < j; ++k) {
                switch (i) {
                    case 0:
                        al.add(new Character('+'));
                        break;
                    case 1:
                        al.add(new Character('-'));
                        break;
                    case 2:
                        al.add(new Character('x'));
                        break;
                    case 3:
                        al.add(new Character('/'));
                        break;
                    default:
                        break;
                }
            }
        }

        rec(N - 1, (ArrayList<Character>) al.clone());

        bw.write(String.valueOf(max));
        bw.newLine();
        bw.write(String.valueOf(min));

        bw.close();
    }

    public void rec(int maxOps, ArrayList<Character> myOriginalOps) {
        if (ops.size() == maxOps) {
            calculate();
            return;
        }

        for (int i = 0; i < myOriginalOps.size(); ++i) {
            ops.add(myOriginalOps.get(i));
            ArrayList<Character> nextOps = (ArrayList<Character>) (myOriginalOps.clone());
            nextOps.remove(i);
            rec(maxOps, nextOps);
            ops.remove(ops.size() - 1);
            nextOps.clear();
        }
    }

    public void calculate() {
        int r = An[0];

        for (int i = 1; i < An.length; ++i) {
            int idxOps = i - 1;

            switch (ops.get(idxOps)) {
                case '+':
                    r = r + An[i];
                    break;
                case '-':
                    r = r - An[i];
                    break;
                case 'x':
                    r = r * An[i];
                    break;
                case '/':
                    r = r / An[i];
                    break;
            }
        }

        min = Math.min(min, r);
        max = Math.max(max, r);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}