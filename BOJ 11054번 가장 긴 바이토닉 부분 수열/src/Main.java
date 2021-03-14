import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ 11054번 가장 긴 바이토닉 부분 수열
 *
 * 유튜브 문제 풀이 :
 *
 * 문제링크 : https://www.acmicpc.net/problem/11054
 *
 * 자바소스 :
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[] dt;
    int[] dp;
    int[] dpL2R;
    int[] dpR2L;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dt = new int[N+2];
        dpL2R = new int[N+2];
        dpR2L = new int[N+2];
        dp = new int[N+2];

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; ++i) {
            dt[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=1; i<=N; ++i) {
            for (int j=0; j<i; ++j) {
                if (dt[j] < dt[i]) {
                    dpL2R[i] = Math.max(dpL2R[j] +1, dpL2R[i]);
                }
            }
        }

        for (int i=N; i>=1; --i) {
            for (int j=N+1; j>i; --j) {
                if (dt[i] > dt[j]) {
                    dpR2L[i] = Math.max(dpR2L[j] +1, dpR2L[i]);
                }
            }
        }

        int max = 0;

        for (int i=1; i<=N; ++i) {
            int maxL = 0;
            int maxR = 0;

            for (int l=0; l<i; ++l) {
                if (dt[l] < dt[i]) {
                    maxL = Math.max(dpL2R[l], maxL);
                }
            }

            for (int r=N+1; i<r; --r) {
                if (dt[i] > dt[r]) {
                    maxR = Math.max(dpR2L[r], maxR);
                }
            }

            max = Math.max(maxL + maxR +1, max);
        }

        int r = max;

        bw.write(String.valueOf(r));
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}