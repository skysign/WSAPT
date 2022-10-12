import java.io.*;
import java.util.ArrayList;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int UP = 1;
    public static int UL = 2;
    public static int LT = 3;
    public static int DL = 4;
    public static int DN = 5;
    public static int DR = 6;
    public static int RT = 7;
    public static int UR = 8;

    int maxSum = 0;

    public int[][] dRC = new int[][]{
            {0, 0},
            {-1, 0},
            {-1, -1},
            {0, -1},
            {1, -1},
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1}
    };

    public class Fish {
        public int mNum;
        public int mDir;
        public int mR;
        public int mC;
        public int mDead;

        public Fish(int num, int dir, int r, int c) {
            mNum = num;
            mDir = dir;
            mR = r;
            mC = c;
            mDead = 0;
        }

        public void set(int[] data) {
            mNum = data[0];
            mDir = data[1];
            mR = data[2];
            mC = data[3];
            mDead = data[4];
        }

        public int[] get() {
            return new int[]{mNum, mDir, mR, mC, mDead};
        }
    }

    public class Shark extends Fish {
        public Shark(int num, int dir, int r, int c) {
            super(num, dir, r, c);
        }

        public Fish eat(Fish fish, int mySum) {
            fish.mDead = 1;
            mDir = fish.mDir;
            mR = fish.mR;
            mC = fish.mC;

            StringBuilder sb = new StringBuilder();
            sb.append("Shark eat Fish #");
            sb.append(fish.mNum);
            sb.append(" current sum:");
            sb.append(mySum + fish.mNum);
            sb.append("/maxSum:");
            sb.append(maxSum);
            printMap(sb.toString());

            return fish;
        }
    }

    Fish[][] map;
    Shark shark = new Shark(0, 0, 1, 1);
    Fish[] fishes = null;
    String[] strs;

    public void solve() throws IOException {
        map = new Fish[4 + 2][4 + 2];
        fishes = new Fish[4 * 4 + 1];

        for (int r = 0; r < 4; ++r) {
            strs = br.readLine().split(" ");

            for (int c = 0; c < 4; ++c) {
                int num = Integer.parseInt(strs[c * 2 + 0]);
                int dir = Integer.parseInt(strs[c * 2 + 1]);
                Fish fish = new Fish(num, dir, r + 1, c + 1);
                map[r + 1][c + 1] = fish;
                fishes[num] = fish;
            }
        }

        printMap("No fish moves");

        // 상어가 수조에 들어와서,
        // 0,0 에 있는 물고기를 잡아 먹는다
        Fish eatenFish = shark.eat(map[1][1], maxSum);
        maxSum += eatenFish.mNum;

        int r = backTracking(0, maxSum);

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public int backTracking(int depth, int mySum) {
        // 물고기 이동
        for (int idxFish = 1; idxFish < fishes.length; ++idxFish) {
            if (fishes[idxFish].mDead == 1) {
                StringBuilder sb = new StringBuilder();
                sb.append("Depth ");
                sb.append(depth);
                sb.append(' ');
                sb.append(" Fish #");
                sb.append(idxFish);
                sb.append(' ');
                sb.append("is already eaten, no moving");
                printMap(sb.toString());
                continue;
            }


            Fish fish = fishes[idxFish];
            int r = fish.mR;
            int c = fish.mC;
            int dir = fish.mDir;
            int dr = dRC[dir][0];
            int dc = dRC[dir][1];

            while (((r + dr == shark.mR) && (c + dc == shark.mC)) || (map[r + dr][c + dc] == null)) {
                dir = rotate45byCCW(dir);
                dr = dRC[dir][0];
                dc = dRC[dir][1];
            }

            fish.mDir = dir;

            swapFishes(r, c, r + dr, c + dc);

            StringBuilder sb = new StringBuilder();
            sb.append("Depth ");
            sb.append(depth);
            sb.append(' ');
            sb.append(" Fish #");
            sb.append(idxFish);
            sb.append(' ');
            sb.append("Moved");
            printMap(sb.toString());
        }

        // 상어 이동
        int sr = shark.mR;
        int sc = shark.mC;
        int dir = shark.mDir;
        int dr = dRC[dir][0];
        int dc = dRC[dir][1];
        int r = sr + dr;
        int c = sc + dc;

        int myMaxSum = mySum;

        StringBuilder sb = new StringBuilder();
        sb.append(" Depth ");
        sb.append(depth);
        sb.append(" shark move ");
        printMap(sb.toString());

        while (map[r][c] != null) {
            if (map[r][c].mDead != 1) {
                // 현재 맵을 저장
                ArrayList<int[]> al = new ArrayList<>();
                saveMap(al);

                int[] preShark = shark.get();
                Fish eatenFish = shark.eat(map[r][c], mySum);

                int tmpMySum = backTracking(depth + 1, mySum + eatenFish.mNum);

                shark.set(preShark);

                restoreMap(al);
                al.clear();

                StringBuilder sb2 = new StringBuilder();
                sb2.append(" Depth ");
                sb2.append(depth);
                sb2.append(' ');
                sb2.append(" map is restored /");
                sb2.append(" current sum:");
                sb2.append(mySum);
                printMap(sb2.toString());

                myMaxSum = Math.max(myMaxSum, tmpMySum);
            }

            r = r + dr;
            c = c + dc;
        }

        StringBuilder sb3 = new StringBuilder();
        sb3.append(" Depth ");
        sb3.append(depth);
        sb3.append(' ');
        sb3.append(" return ");
        sb3.append(myMaxSum);
        printMap(sb3.toString());

        return myMaxSum;
    }

    public void saveMap(ArrayList<int[]> al) {
        for (int r = 1; r < map.length - 1; ++r) {
            for (int c = 1; c < map[0].length - 1; ++c) {
                al.add(map[r][c].get());
            }
        }
    }

    public void restoreMap(ArrayList<int[]> al) {
        int idx = 0;

        for (int r = 1; r < map.length - 1; ++r) {
            for (int c = 1; c < map[0].length - 1; ++c) {
                int[] data = al.get(idx);

                map[data[2]][data[3]].set(data);
                Fish fish = map[data[2]][data[3]];
                fishes[fish.mNum] = fish;

                ++idx;
            }
        }
    }

    public int rotate45byCCW(int dir) {
        if (UR == dir)
            return 1;

        return dir + 1;
    }

    public void swapFishes(int sr, int sc, int dr, int dc) {
        map[sr][sc].mR = dr;
        map[sr][sc].mC = dc;

        map[dr][dc].mR = sr;
        map[dr][dc].mC = sc;

        Fish tmp = map[sr][sc];
        map[sr][sc] = map[dr][dc];
        map[dr][dc] = tmp;
    }

    public void printMap(String msg) {
//        try {
//            bw.write(msg);
//            bw.newLine();
//
//            for (int r = 1; r < map.length - 1; ++r) {
//                for (int c = 1; c < map[0].length - 1; ++c) {
//                    int dir = map[r][c].mDir;
//
//                    if (map[r][c].mDead == 1) {
//                        if (r == shark.mR && c == shark.mC) {
//                            dir = shark.mDir;
//                            bw.write("*S");
//                        }
//                        else {
//                            dir = 0;
//                            bw.write("[E");
//                        }
//                    } else {
//                        String str = String.format("%2d", map[r][c].mNum);
//                        bw.write(str);
//                    }
//
//                    bw.write(String.valueOf(' '));
//
////                    ↑, ↖, ←, ↙, ↓, ↘, →, ↗
////                    1   2   3   4   5   6   7   8
//                    switch (dir) {
//                        case 1:
//                            bw.write(String.valueOf('↑'));
//                            break;
//                        case 2:
//                            bw.write(String.valueOf('↖'));
//                            break;
//                        case 3:
//                            bw.write(String.valueOf('←'));
//                            break;
//                        case 4:
//                            bw.write(String.valueOf('↙'));
//                            break;
//                        case 5:
//                            bw.write(String.valueOf('↓'));
//                            break;
//                        case 6:
//                            bw.write(String.valueOf('↘'));
//                            break;
//                        case 7:
//                            bw.write(String.valueOf('→'));
//                            break;
//                        case 8:
//                            bw.write(String.valueOf('↗'));
//                            break;
//                        default:
//                            bw.write(String.valueOf(' '));
//                            break;
//                    }
//
//                    if (map[r][c].mDead == 1) {
//                        if (r == shark.mR && c == shark.mC) {
//                            bw.write(String.valueOf(dir));
//                        }
//                        else {
//                            bw.write(" ]");
//                        }
//                    } else {
//                        bw.write(String.valueOf(dir));
//                    }
//                    bw.write("  ");
//                }
//
//                bw.newLine();
//            }
//
//            bw.newLine();
//            bw.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}