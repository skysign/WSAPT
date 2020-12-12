import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    int N;
    char[][] map;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];

        for (int i = 0; i < N; ++i) {
            String line = br.readLine();

            for (int j = 0; j < N; ++j) {
                map[i][j] = line.charAt(j);
            }
        }

        BFSNormal bfsNormal = new BFSNormal();
        bfsNormal.setSameColor(bfsNormal);
        BFSRedGreenColorBlindness bfsRGCB = new BFSRedGreenColorBlindness();
        bfsRGCB.setSameColor(bfsRGCB);

        int rNormal = bfsNormal.countAreas();
        bfsNormal = null;
        int rRGCB = bfsRGCB.countAreas();
        bfsRGCB = null;

        bw.write(Integer.toString(rNormal));
        bw.write(' ');
        bw.write(Integer.toString(rRGCB));

        bw.flush();
        bw.close();
    }

    interface sameColor {
        boolean isSameColor(int y, int x, char color);
    }

    class BFSRedGreenColorBlindness extends BFS implements sameColor {
        @Override
        public boolean isSameColor(int y, int x, char color) {
            char dstColor = map[y][x];

            if (('R' == color) || ('G' == color)) {
                return ((dstColor == 'R') || (dstColor == 'G'));
            }

            return (dstColor == color);
        }
    }

    class BFSNormal extends BFS implements sameColor {
        @Override
        public boolean isSameColor(int y, int x, char color) {
            char dstColor = map[y][x];
            return (dstColor == color);
        }
    }

    class BFS {
        sameColor mSameColor;

        public void setSameColor(sameColor implSameColor) {
            mSameColor = implSameColor;
        }

        int countAreas() {
            int r = 0;
            boolean[][] visitied = new boolean[N][N];

            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (false == visitied[i][j]) {
                        ++r;
                        searchBFS(i, j, visitied);
                    }
                }
            }

            visitied = null;

            return r;
        }

        void searchBFS(int i, int j, boolean[][] visitied) {
            Deque<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{i, j});

            while (queue.size() > 0) {
                int[] item = queue.pop();
                int sy = item[0];
                int sx = item[1];
                visitied[sy][sx] = true;

                char color = map[sy][sx];

                for(int idx = 0; idx < d4i.length; ++idx) {
                    int dy = sy + d4i[idx];
                    int dx = sx + d4j[idx];

                    if (isIn(dy, dx) && (false == visitied[dy][dx])) {
                        if(mSameColor.isSameColor(dy, dx, color)) {
                            queue.add(new int[]{dy, dx});
                        }
                    }
                }
            }
        }

        boolean isIn(int y, int x) {
            return ((0<=x) && (x<N) && (0<=y) && (y<N));
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}