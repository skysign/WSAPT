/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 11780번 플로이드 2
 *
 * 유튜브 문제 풀이: https://youtu.be/Mnxwu2mIZWI
 *
 * 문제링크: https://www.acmicpc.net/problem/11780
 *
 * 자바소스: https://bit.ly/2TfdJsb
 */

import java.io.*;
import java.util.ArrayList;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        Main main = new Main();
        main.solve();
    }

    int N;
    int M;
    String[] strs;
    int[][] dt;
    ArrayList[][] path;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dt = new int[N+1][N+1];
        path = new ArrayList[N+1][N+1];

        for (int i=0; i<M; ++i) {
            strs = br.readLine().split(" ");
            int fr = Integer.parseInt(strs[0]);
            int to = Integer.parseInt(strs[1]);
            int v = Integer.parseInt(strs[2]);

            if (null == path[fr][to]) {
                path[fr][to] = new ArrayList();
            }

            if (dt[fr][to] == 0) {
                dt[fr][to] = v;

                path[fr][to].clear();
                path[fr][to].add(fr);
                path[fr][to].add(to);
            }
            else if (v < dt[fr][to]) {
                dt[fr][to] = v;
                path[fr][to].clear();
                path[fr][to].add(fr);
                path[fr][to].add(to);
            }
        }

        for (int mid=1; mid<N+1; ++mid) {
            for (int fr=1; fr<N+1; ++fr) {
                for (int to=1; to<N+1; ++to) {
                    if (dt[fr][mid] == 0)
                        continue;
                    if (dt[mid][to] == 0)
                        continue;

                    if ((fr == mid) || (mid == to) || fr == to)
                        continue;

                    if ((dt[fr][to] == 0) || (dt[fr][to] > (dt[fr][mid] + dt[mid][to]))) {
                        dt[fr][to] = (dt[fr][mid] + dt[mid][to]);

                        if (path[fr][to] == null)
                            path[fr][to] = new ArrayList();

                        path[fr][to].clear();
                        path[fr][to].addAll(path[fr][mid]);
                        path[fr][to].addAll(path[mid][to].subList(1, path[mid][to].size()));
                    }
                }
            }
        }

        for (int i=1; i<N+1; ++i) {
            for (int j=1; j<N+1; ++j) {
                bw.write(String.valueOf(dt[i][j]));
                bw.write(" ");
            }

            bw.newLine();
        }

        for (int i=1; i<N+1; ++i) {
            for (int j=1; j<N+1; ++j) {
                if (dt[i][j] == 0)
                    bw.write(String.valueOf(0));
                else {
                    bw.write(String.valueOf(path[i][j].size()));
                    bw.write(" ");

                    for (Object o: path[i][j]) {
                        Integer v = (Integer)o;
                        bw.write(String.valueOf(v));
                        bw.write(" ");
                    }
                }

                bw.newLine();
            }
        }

        bw.close();
    }
}