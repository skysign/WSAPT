import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[][] dt;
    int[][] map;
    int[][] n2ij;

    // big O
    // 20 * 20 400
    // 4가지 방향 확인
    // 각 방향마다, 숫자 확인

    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        dt = new int[N * N][5];
        n2ij = new int[(N * N) + 1][2];

        map = new int[N + 2][N + 2];

        for (int i = 0; i < map.length; ++i) {
            map[0][i] = -1; // 제일 윗줄
            map[map.length - 1][i] = -1; // 제일 아랫줄
            map[i][0] = -1; // 첫번째 컬럼
            map[i][map.length - 1] = -1; // 마지막 컬럼
        }

        for (int i = 0; i < (N * N); ++i) {
            String[] strs = br.readLine().split(" ");

            for (int j = 0; j < 5; ++j) {
                dt[i][j] = Integer.parseInt(strs[j]);
            }
        }

        int t = (N+2) / 2;
        map[t][t] = dt[0][0];
        n2ij[dt[0][0]][0] = t;
        n2ij[dt[0][0]][1] = t;

        for (int i = 1; i < dt.length; ++i) {
            int[] yx = findMyPosition(dt[i]);
            int y = yx[0];
            int x = yx[1];
            map[y][x] = dt[i][0];

            n2ij[dt[i][0]][0] = y;
            n2ij[dt[i][0]][1] = x;
        }

        int r = score();
        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public int[] findMyPosition(int[] student) {
        ArrayList<int[]> al = findForRuleN1(student);
        if (al == null)
            return null;

        ArrayList<int[]> al2 = findForRuleN2(al);
        if (al2 == null)
            return null;

        int[] yx = findForRuleN3(al2);

        return yx;
    }

    int[] findForRuleN3(ArrayList<int[]> al) {
        Collections.sort(al, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
//                int r = Integer.compare(o1[1], o2[1]);
//                if (0 == r) {
//                    return Integer.compare(o1[0], o2[0]);
//                }
//
//                return r;

                int r = Integer.compare(o1[0], o2[0]);
                if (0 == r) {
                    return Integer.compare(o1[1], o2[1]);
                }

                return r;
            }
        });

        return al.get(0);
    }


    ArrayList<int[]> findForRuleN2(ArrayList<int[]> al) {
        ArrayList<int[]> al2 = new ArrayList<>();

        for (int[] t : al) {
            int i = t[0];
            int j = t[1];

            int nOfEmpty = 0;

            for (int idx = 0; idx < d4i.length; ++idx) {
                int ny = i + d4i[idx];
                int nx = j + d4j[idx];

                if (map[ny][nx] == 0)
                    nOfEmpty++;
            }

            t[2] = nOfEmpty;

            al2.add(t);
        }

        if (al2.size() == 0)
            return null;

        al = al2;

        int biggest = al.get(al.size() - 1)[2];

        boolean bContinue = true;

        while (bContinue) {
            int[] t = al.get(0);

            if (t[2] < biggest) {
                al.remove(0);
            } else {
                bContinue = false;
            }
        }

        return al;
    }

//            for (int i = 1; i < map.length - 1; ++i) {
//        for (int j = 1; i < map[0].length - 1; ++j) {
//            if (map[i][j] != 0)
//                continue;
//
//            for (int idx = 0; idx < d4i.length; ++idx) {
//                int ny = i + d4i[idx];
//                int nx = j + d4j[idx];
//            }
//        }
//    }

    ArrayList<int[]> findForRuleN1(int[] likes) {
        ArrayList<int[]> al = new ArrayList<>();

        for (int i = 1; i < map.length - 1; ++i) {
            for (int j = 1; j < map[0].length - 1; ++j) {
                if (map[i][j] != 0)
                    continue;

                int nOfLikeStudent = 0;

                for (int idx = 0; idx < d4i.length; ++idx) {
                    int ny = i + d4i[idx];
                    int nx = j + d4j[idx];

                    for (int k = 1; k < likes.length; ++k) {
                        if (map[ny][nx] == likes[k]) {
                            nOfLikeStudent++;
                        }
                    }
                }

//                if (nOfLikeStudent > 0) {
//                    al.add(new int[]{i, j, nOfLikeStudent});
//                }
                al.add(new int[]{i, j, nOfLikeStudent});
            }
        }

        if (al.size() == 0)
            return null;

        Collections.sort(al, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        // 좋아하는 학생이 옆에 앉아 있는 숫자중에, 가장 큰 수
        int biggest = al.get(al.size() - 1)[2];

        boolean bContinue = true;

        // 가장 큰 수 보다, 작은 위치는 모두 삭제함
        while (bContinue) {
            int[] t = al.get(0);

            if (t[2] < biggest) {
                al.remove(0);
            } else {
                // 정렬이 되어 있기 때문에,
                // 한번 작지 않은 값을 만나면,
                // 이후로는 작지 않은 값을 만날 수 없음
                // 루프 종료
                bContinue = false;
            }
        }

        return al;
    }

    public int score() {
        int r = 0;

        for (int i=0; i<dt.length; ++i) {
            int[] std = dt[i];
            int y = n2ij[std[0]][0];
            int x = n2ij[std[0]][1];

            int tR = getScore(std, y, x);

            r += tR;
        }

        return r;
    }

    public int getScore(int[] likes, int i, int j) {
        int nOfLikeStudent = 0;

        for (int idx = 0; idx < d4i.length; ++idx) {
            int ny = i + d4i[idx];
            int nx = j + d4j[idx];

            for (int k = 1; k < likes.length; ++k) {
                if (map[ny][nx] == likes[k]) {
                    nOfLikeStudent++;
                }
            }
        }

        int r = 0;

        switch(nOfLikeStudent) {
            case 1:
                r = 1;
                break;
            case 2:
                r = 10;
                break;
            case 3:
                r = 100;
                break;
            case 4:
                r = 1000;
                break;
            default:
                r = 0;
                break;
        }

        return r;
    }

    public static void main(String[] args) throws IOException {
//        try {
            Main main = new Main();
            main.solve();
//        }
//        catch (Exception e){
//            System.out.println(e.toString());
//        }

    }
}