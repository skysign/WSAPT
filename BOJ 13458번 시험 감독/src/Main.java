import java.io.*;

/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 13458번 시험 감독
 *
 * 유튜브 문제 풀이: https://youtu.be/C8ElgcREo2w
 *
 * 문제링크: https://www.acmicpc.net/problem/13458
 *
 * 자바소스: https://bit.ly/3GhhfVY
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int cntJang;
    long[] jang;
    long ju, bu;
    String[] strs;

    public void solve() throws IOException {
        cntJang = Integer.parseInt(br.readLine());
        jang = new long[cntJang];
        strs = br.readLine().split(" ");

        for (int i = 0; i < cntJang; ++i) {
            jang[i] = Integer.parseInt(strs[i]);
        }

        strs = br.readLine().split(" ");
        ju = Integer.parseInt(strs[0]);
        bu = Integer.parseInt(strs[1]);

        long r = cntJang;

        for (int i = 0; i < cntJang; ++i) {
            jang[i] -= ju;

            if (jang[i] > 0) {
                r += (jang[i] / bu);

                if (jang[i] % bu > 0) {
                    r++;
                }
            }
        }

        bw.write(String.valueOf(r));
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}