import java.io.*;
import java.util.ArrayList;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    String[] strs;
    ArrayList<int[]> houses = new ArrayList<>();
    ArrayList<int[]> chickens = new ArrayList<>();

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        for (int y = 0; y < N; ++y) {
            strs = br.readLine().split(" ");
            for (int x = 0; x < N; ++x) {
                int v = Integer.parseInt(strs[x]);

                // 집
                if (v == 1) {
                    houses.add(new int[]{y, x});
                }
                // 치킨집
                else if (v == 2) {
                    chickens.add(new int[]{y, x});
                }
            }
        }

        int[] arrChickens = new int[chickens.size()];
        for (int i = 0; i < arrChickens.length; ++i) {
            arrChickens[i] = i;
        }

        boolean[] arrChickensVisited = new boolean[chickens.size()];
        ArrayList<int[]> al = new ArrayList<>();

        combination(arrChickens, arrChickensVisited, 0, chickens.size(), M, M, al);

        int r = Integer.MAX_VALUE;

        for(int[] selectedChickens: al) {
            int tr = 0;
            for(int[] house: houses) {
                int t = chickenDistanceFromHouse(house, selectedChickens);
                tr += t;
            }

            r = Math.min(r, tr);
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    int distance(int[] house, int[] chickens) {
        return Math.abs(house[0] - chickens[0]) + Math.abs(house[1] - chickens[1]);
    }

    int chickenDistanceFromHouse(int[] house, int[] selectedChickens) {
        int r = Integer.MAX_VALUE;

        for (int idxChicken : selectedChickens) {
            r = Math.min(r, distance(house, chickens.get(idxChicken)));
        }

        return r;
    }

    void combination(int[] arr, boolean[] visited, int start, int n, int r, int R, ArrayList<int[]> al) {
        if (r == 0) {
            print(arr, visited, n, R, al);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1, R, al);
            visited[i] = false;
        }
    }

    void print(int[] arr, boolean[] visited, int n, int R, ArrayList<int[]> al) {
        int[] comb = new int[R];

        for (int i = 0, idx = 0; i < n; i++) {
            if (visited[i] == true) {
//                System.out.print(arr[i] + " ");
                comb[idx] = arr[i];
                idx++;
            }
        }

        al.add(comb);
//        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}