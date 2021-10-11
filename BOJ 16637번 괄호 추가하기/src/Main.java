/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 16637번 괄호 추가하기
 *
 * 유튜브 문제 풀이: https://youtu.be/kHvaAFmZ5z4
 *
 * 문제링크: https://www.acmicpc.net/problem/16637
 *
 * 자바소스: https://bit.ly/3iPPdYi
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    ArrayList<Character> mAlOp;
    ArrayList<Long> mAlN;
    int[] mBraces;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        String dt = br.readLine();

        mAlOp = new ArrayList<>();
        mAlN = new ArrayList<>();

        for (int i = 0; i < dt.length(); ++i) {
            char c = dt.charAt(i);

            switch (c) {
                case '+':
                case '-':
                case '*':
                    mAlOp.add(c);
                    break;
                default:
                    mAlN.add(Long.parseLong(String.valueOf(c)));
                    break;
            }
        }

        mBraces = new int[mAlOp.size() +2];

        long r = brace(0, mAlOp.size(), mBraces);

        bw.write(String.valueOf(r));
        bw.close();
    }

    public long brace(int idx, int end, int[] braces) {
        if (!(idx < end)) {
            return calculate(end, braces);
        }

        // 괄호를 안함
        int[] b1 = Arrays.copyOf(braces, braces.length);
        long r1 = brace(idx + 1, end, b1);

        // 괄호를 함
        int[] b2 = Arrays.copyOf(braces, braces.length);
        b2[idx] = 1;
        b2[idx + 1] = 0;
        long r2 = brace(idx + 2, end, b2);

        return Math.max(r1, r2);
    }

    public long calculate(int end, int[] braces) {
        ArrayList<Character> alOp = new ArrayList<>();
        ArrayList<Long> alN = new ArrayList<>();

        alN.add(mAlN.get(0));

        for (int i = 0; i < end; ++i) {
            if (braces[i] == 1) {
                long a = alN.get(alN.size() - 1);
                long b = mAlN.get(i+1);
                long t = cal(a, b, mAlOp.get(i));

                alN.remove(alN.size()-1);
                alN.add(t);
            } else {
                alOp.add(mAlOp.get(i));
                alN.add(mAlN.get(i+1));
            }
        }

        long v = alN.get(0);
        alN.remove(0);

        for (int i=0; i<alOp.size(); ++i) {
            long b = alN.get(i);
            char op = alOp.get(i);
            v = cal(v, b, op);
        }

        return v;
    }

    public long cal(long a, long b, char op) {
        long r = 0;

        switch (op) {
            case '+':
                r = a + b;
                break;

            case '-':
                r = a - b;
                break;

            case '*':
                r = a * b;
                break;
        }

        return r;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}