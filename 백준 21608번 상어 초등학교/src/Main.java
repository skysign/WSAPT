import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[][] map;
    int[][] mapSati;

    HashMap<Integer, Set<Integer>> hm = new HashMap<>();

    String[] strs;
    int[][] dRC = new int[][]{
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    ArrayList<int[]>[] candidates = new ArrayList[5];
    ArrayList<int[]>[] candiEmpties = new ArrayList[5];
    ArrayList<Integer> students = new ArrayList<>();

    public void solve() throws Exception {
        candidates[0] = new ArrayList<>();
        candidates[1] = new ArrayList<>();
        candidates[2] = new ArrayList<>();
        candidates[3] = new ArrayList<>();
        candidates[4] = new ArrayList<>();

        candiEmpties[0] = new ArrayList<>();
        candiEmpties[1] = new ArrayList<>();
        candiEmpties[2] = new ArrayList<>();
        candiEmpties[3] = new ArrayList<>();
        candiEmpties[4] = new ArrayList<>();

        N = Integer.parseInt(br.readLine());

        map = new int[N + 2][N + 2];
        mapSati = new int[N + 2][N + 2];

        for (int idx = 0; idx < N + 2; ++idx) {
            map[idx][0] = -1;
            map[idx][N + 2 - 1] = -1;

            map[0][idx] = -1;
            map[N + 2 - 1][idx] = -1;
        }

        int N_N = N * N;

        for (int i = 0; i < N_N; ++i) {
            strs = br.readLine().split(" ");

            int student = -1;
            HashSet<Integer> likes = new HashSet<>();

            for (int j = 0; j < 5; ++j) {
                int v = Integer.parseInt(strs[j]);

                if (0 == j) {
                    student = v;
                    students.add(student);
                } else {
                    likes.add(v);
                }
            }

            hm.put(student, likes);
        }

        for (int student : students) {
            Set<Integer> likes = hm.get(student);

            // 1번 룰 코딩
            // 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
            for (int r = 1; r <= N; ++r) {
                for (int c = 1; c <= N; ++c) {
                    if (map[r][c] > 0)
                        continue;

                    int cntLikeStudent = 0;

                    for (int[] drc : dRC) {
                        int dr = drc[0];
                        int dc = drc[1];

                        int dstR = r + dr;
                        int dstC = c + dc;

                        int likeStudent = map[dstR][dstC];

                        if (likes.contains(likeStudent)) {
                            ++cntLikeStudent;
                        }
                    }

                    candidates[4 - cntLikeStudent].add(new int[]{r, c});
                }
            }

            // 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
            // 비어있는 칸의 개수에 따라서, candiEmpties[x] 에 r,c 를 추가함
            for (ArrayList<int[]> candi : candidates) {
                if (candi.size() > 0) {
                    for (int[] rc : candi) {
                        int r = rc[0];
                        int c = rc[1];

                        int cntEmpty = 0;

                        for (int[] drc : dRC) {
                            int dr = drc[0];
                            int dc = drc[1];

                            int dstR = r + dr;
                            int dstC = c + dc;

                            if (map[dstR][dstC] == 0) {
                                ++cntEmpty;
                            }
                        }

                        candiEmpties[4 - cntEmpty].add(new int[]{r, c});
                    }

                    break;
                }
            }

            for (ArrayList<int[]> candi : candidates) {
                candi.clear();
            }

            int bestR = Integer.MAX_VALUE, bestC = Integer.MAX_VALUE;

            for (ArrayList<int[]> candi : candiEmpties) {
                if (candi.size() > 0) {
                    for (int[] rc : candi) {
                        int r = rc[0];
                        int c = rc[1];

                        if (r < bestR) {
                            bestR = r;
                            bestC = c;
                        } else if (r == bestR) {
                            if (c < bestC) {
                                bestR = r;
                                bestC = c;
                            }
                        }
                    }

                    break;
                }
            }

            for (ArrayList<int[]> candi : candiEmpties) {
                candi.clear();
            }

            map[bestR][bestC] = student;
        }


        int ans = 0;

        for (int r = 1; r <= N; ++r) {
            for (int c = 1; c <= N; ++c) {
                Set<Integer> likes = hm.get(map[r][c]);

                int cntLikeStudent = 0;

                for (int[] drc : dRC) {
                    int dr = drc[0];
                    int dc = drc[1];

                    int dstR = r + dr;
                    int dstC = c + dc;

                    int likeStudent = map[dstR][dstC];

                    if (likes.contains(likeStudent)) {
                        ++cntLikeStudent;
                    }
                }

                mapSati[r][c] = cntLikeStudent;

                switch (cntLikeStudent) {
                    case 0:
                        ans += 0;
                        break;
                    case 1:
                        ans += 1;
                        break;
                    case 2:
                        ans += 10;
                        break;
                    case 3:
                        ans += 100;
                        break;
                    case 4:
                        ans += 1000;
                        break;
                    default:
                        throw new Exception("wrong cntLikeStudent");
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.solve();
    }
}