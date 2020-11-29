import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    Map<Integer, Boolean> map = new HashMap<>();

    public void solve() throws IOException {
        // 10000미만의 모든 소수를 구하고
        SieveOfEratosthenes(10000, map);

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

                            if (map.containsKey(number)) {
                                que.add(new int[]{number, item[1] + 1});
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

    public void SieveOfEratosthenes(int n, Map<Integer, Boolean> map) {
        boolean[] bPrimes = new boolean[n];

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

        // 1000이하 소수들은 모두 소수가 아닌 것으로 처리
        for (int i = 1000; i<n; ++i) {
            if (bPrimes[i])
                map.put(i, bPrimes[i]);
        }

        bPrimes = null;

        System.gc();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}