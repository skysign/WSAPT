import java.io.*;
import java.util.*;

/**
 * BOJ 20947번 습격받은 도시
 *
 * 유튜브 문제 풀이 : https://youtu.be/joTS2ATidt0
 *
 * 문제링크 : https://www.acmicpc.net/problem/20947
 *
 * 자바소스 : https://bit.ly/2PTSw5v
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    char[][] map;
    boolean[][] map2;
    ArrayList<int[]> Xs;
    ArrayList<int[]> Os;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        map2 = new boolean[N][N];
        Xs = new ArrayList<>();
        Os = new ArrayList<>();

        // 맵을 입력 받습니다.
        for (int i=0; i<N; ++i) {
            String strLine = br.readLine();

            for (int j=0; j<N; ++j) {
                map[i][j] = strLine.charAt(j);

                if (map[i][j] == 'X') {
                    // 잔해위치도 저장해 둡니다.
                    Xs.add(new int[]{i, j});
                }
                else if (map[i][j] == 'O') {
                    // 건물위치도 저장해 둡니다.
                    Os.add(new int[]{i, j});
                }
            }
        }

        // 폭탄을 놓을 수 있는 곳을 true로 체크
        for(int[] yx: Xs) {
            int sy = yx[0];
            int sx = yx[1];
            for (int idx=0; idx<d4i.length; ++idx) {
                int dy = d4i[idx];
                int dx = d4j[idx];

                int y = sy + dy;
                int x = sx + dx;

                while ((0<=y) && (y<N) && (0<=x) && (x<N) && (map[y][x] == '.')) {
                    map2[y][x] = true;
                    y += dy;
                    x += dx;
                }
            }
        }

        // 폭탄을 놓을 수 없는 곳을 false로 체크
        for(int[] yx: Os) {
            int sy = yx[0];
            int sx = yx[1];

            for (int idx=0; idx<d4i.length; ++idx) {
                int dy = d4i[idx];
                int dx = d4j[idx];

                int y = sy + dy;
                int x = sx + dx;

                while ((0<=y) && (y<N) && (0<=x) && (x<N) && (map[y][x] == '.')) {
                    map2[y][x] = false;
                    y += dy;
                    x += dx;
                }
            }
        }

        // 폭탄을 놓을 수 있는 모든 곳에, 폭탄을 배치하기
        for (int y=0; y<N; ++y) {
            for (int x=0; x<N; ++x) {
                if (map2[y][x]) {
                    map[y][x] = 'B';
                }
            }
        }

        // 맵을 출력해주는 코드
        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                bw.write(map[i][j]);
            }
            bw.newLine();
        }

        bw.close();
    }

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}