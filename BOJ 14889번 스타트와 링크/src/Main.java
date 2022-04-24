import java.io.*;

/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 14889번 스타트와 링크
 *
 * 유튜브 문제 풀이: https://youtu.be/IIK-vd4iMp8
 *
 * 문제링크: https://www.acmicpc.net/problem/14889
 *
 * 자바소스: https://bit.ly/39F4Zkp
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String[] strs;
    int[][] map;
    int[] allMembers;
    boolean[] isStartMembers;

    int r = Integer.MAX_VALUE;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        allMembers = new int[N];
        isStartMembers = new boolean[N];

        for (int i = 0; i < N; ++i) {
            allMembers[i] = (i + 1);
            strs = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                map[i + 1][j + 1] = Integer.parseInt(strs[j]);
            }
        }

        recursiveFunction(0, 0);

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public void recursiveFunction(int depth, int idx) {
        if (depth == (N / 2)) {
            calculate();
            return;
        }

        for (int i = idx; i < allMembers.length; ++i) {
            if (isStartMembers[i])
                continue;

            isStartMembers[i] = true;
            recursiveFunction(depth + 1, i + 1);
            isStartMembers[i] = false;
        }
    }

    public void calculate() {
        int[] startMembers = new int[N / 2];
        int[] linkMembers = new int[N / 2];

        int idxStartMembers = 0;
        int idxLinkMembers = 0;

        for (int i = 0; i < isStartMembers.length; ++i) {
            if (isStartMembers[i]) {
                startMembers[idxStartMembers] = i + 1;
                ++idxStartMembers;
            } else {
                linkMembers[idxLinkMembers] = i + 1;
                ++idxLinkMembers;
            }
        }

        int sumStart = 0;
        int sumLink = 0;

        for (int i = 0; i < (N / 2); ++i) {
            for (int j = 0; j < (N / 2); ++j) {
                sumStart += map[startMembers[i]][startMembers[j]];
                sumLink += map[linkMembers[i]][linkMembers[j]];
            }
        }

        r = Math.min(r, Math.abs(sumStart - sumLink));
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}