/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 1021번 회전하는 큐
 *
 * 유튜브 문제 풀이: https://youtu.be/eAPgB7UXBbA
 *
 * 문제링크: https://www.acmicpc.net/problem/1021
 *
 * 자바소스: https://bit.ly/37Zfjm5
 */

import java.io.*;
import java.util.ArrayList;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    int[] dt;
    String[] strs;

    ArrayList<Integer> que;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        dt = new int[M];
        strs = br.readLine().split(" ");

        for (int i = 0; i < M; ++i)
            dt[i] = Integer.parseInt(strs[i]);

        que = new ArrayList<>();

        for (int i = 1; i <= N; ++i)
            que.add(i);

        int r = 0;
        int left = 0;
        int right = 0;

        for (int m = 0; m < M; ++m) {
            int target = dt[m];

            for (int step = 0; step < que.size(); ++step) {
                if (target == que.get(step)) {
                    left = step;
                    right = que.size() - step;
                    break;
                }
            }

            if (left < right) {
                for (int i = 0; i < left; ++i) {
                    que.add(que.get(0));
                    que.remove(0);
                }

                r += left;
            } else {
                for (int i = 0; i < right; ++i) {
                    que.add(0, que.get(que.size() - 1));
                    que.remove(que.size() - 1);
                }

                r += right;
            }

            que.remove(0);
        }

        bw.write(String.valueOf(r));
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}