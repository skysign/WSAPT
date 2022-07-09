/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 15685번 드래곤 커브
 *
 * 유튜브 문제 풀이: https://youtu.be/xffCSb2Qh3k
 *
 * 문제링크: https://www.acmicpc.net/problem/15685
 *
 * 자바소스: https://bit.ly/3PbtHvD
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String[] strs;
    int[][] map;

    int[][] dxy = {
            {1, 0},
            {0, -1},
            {-1, 0},
            {0, 1}
    };

    public void solve() throws IOException {
        map = new int[101][101];
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; ++i) {
            strs = br.readLine().split(" ");

            int x1 = Integer.parseInt(strs[0]);
            int y1 = Integer.parseInt(strs[1]);
            int d = Integer.parseInt(strs[2]);
            int g = Integer.parseInt(strs[3]);

            int x = x1 + dxy[d][0];
            int y = y1 + dxy[d][1];

            ArrayList<int[]> al = new ArrayList<>();
            al.add(new int[]{x1, y1});
            al.add(new int[]{x, y});

            drawDragonCurve(al, g);
        }

        int r = 0;

        for (int y = 0; y < 100; ++y) {
            for (int x = 0; x < 100; ++x) {
                if (map[y][x] == 1) {
                    if ((map[y + 1][x] == 1) && (map[y][x + 1] == 1) && (map[y + 1][x + 1] == 1)) {
                        ++r;
                    }
                }
            }
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public void drawDragonCurve(ArrayList<int[]> al, int g) {
        if (g <= 0) {
            for (int[] xy : al) {
                int x = xy[0];
                int y = xy[1];

                map[y][x] = 1;
            }
            return;
        }

        Stack<int[]> tmp = new Stack<>();

        int oriX = al.get(al.size() - 1)[0];
        int oriY = al.get(al.size() - 1)[1];

        // oriX, oriY를기준으로 90도 회전변환을 하기 위해서, oriX, oriY를 0,0으로 이동 시키기 위한 dx, dy값
        int dx = oriX * -1;
        int dy = oriY * -1;

        for (int i = 0; i < al.size() - 1; ++i) {
            int[] xy = al.get(i);

            int x = xy[0];
            int y = xy[1];

            // oriX, oriY 를 0,0으로 여기는 좌표계로 이동함
            x += dx;
            y += dy;

            // 90도 회전 변환
            // (x,y) ---90도 시계방향 회전--> (-y,x)
            int tmpX = x;
            x = -y;
            y = tmpX;


            // oriX, oriY 를 0,0으로 여기는 좌표계에서, 원래 좌표계로 이동함
            x += (dx * -1);
            y += (dy * -1);

            // 회전변환해서, tmp에 add()하기
            tmp.push(new int[]{x, y});
        }

        while (!tmp.empty()) {
            al.add(tmp.pop());
        }

        drawDragonCurve(al, g - 1);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}