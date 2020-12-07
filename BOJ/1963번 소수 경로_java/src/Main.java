import java.io.*;
import java.util.*;

/**
 * BOJ 1963번 소수 경로
 *
 * 유튜브 문제 풀이
 * https://youtu.be/dG6cyKnyJ5o
 *
 * 문제링크 : https://www.acmicpc.net/problem/1963
 *
 * 자바소스 : https://bit.ly/3gwq2YN
 * CPP소스 : https://bit.ly/33Rkz9x
 */

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    boolean[] bPrimes;

    public void solve() throws IOException {
        // 10000미만의 모든 소수를 구하고
        SieveOfEratosthenes(10000);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; ++i) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int numberFr = Integer.parseInt(st2.nextToken());
            int numberTo = Integer.parseInt(st2.nextToken());

            int r = solve2(numberFr, numberTo);

            if (r == -1) {
                bw.write("Impossible");
            }
            else {
                bw.write(Integer.toString(r));
            }

            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public int solve2(int numberFr, int numberTo) {
        int visited[] = new int[10000];
        for (int i = 0; i < visited.length; ++i) {
            visited[i] = Integer.MAX_VALUE;
        }

        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{numberFr, 0});

        while (que.size() > 0) {
            int[] item = que.pop();

            if (item[0] == numberTo) {
                return item[1];
            }
            else {
                int[] ns = new int[]{item[0] / 1000, (item[0]%1000) / 100, (item[0]%100) / 10, (item[0]%10)};
                int[] dns = ns.clone();

                for (int i = 0; i < ns.length; ++i) {
                    for (int j = 0; j < 10; ++j) {
                        if (ns[i] != j) {
                            dns[i] = j;
                            int number = (dns[0] * 1000) + (dns[1] * 100) + (dns[2] * 10) + dns[3];

                            if (bPrimes[number] && (item[1] + 1 < visited[number])) {
                                que.add(new int[]{number, item[1] + 1});
                                visited[number] = item[1] + 1;
                            }

                            dns[i] = ns[i];
                        }
                    }
                }

                ns = null;
                dns = null;
                item = null;
            }
        }

        return -1;
    }

    public void SieveOfEratosthenes(int n) {
        bPrimes = new boolean[n];

        // Both 0 and 1 are not prime numbers
        // Assume from number 2, they are all of prime numbers
        for (int i = 2; i < n; ++i)
            bPrimes[i] = true;

        for (int i = 2; i*i < n; ++i) {
            if (bPrimes[i]) {
                for (int j = i*i; j < n; j+=i)
                    bPrimes[j] = false;
            }
        }

        // 1000이하 소수들은 모두 소수가 아닌 것으로 간주함
        for (int i = 0; i<1000; ++i) {
            if (bPrimes[i])
                bPrimes[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}