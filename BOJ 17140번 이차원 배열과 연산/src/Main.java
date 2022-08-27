import java.io.*;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int R = 3, C = 3, K, ansR, ansC;
    int oldR, oldC, maxR, maxC;
    String[] strs;
    int[][] map;
    HashMap<Integer, Integer> hm[];

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        ansR = Integer.parseInt(strs[0]);
        ansC = Integer.parseInt(strs[1]);
        --ansR;    // 배열의 인덱스는 1작으므로, 1감소 시킨다
        --ansC;
        K = Integer.parseInt(strs[2]);

        map = new int[100][100];


        for (int i = 0; i < 3; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 0; j < 3; ++j) {
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        int ans = -1;

        // R, C 연산을 하지 않아도, 되는 경우
        if (map[ansR][ansC] == K) {
            bw.write(String.valueOf(0));
            bw.newLine();
            bw.close();
            System.exit(0);
        }

        for (int t = 1; t <= 100; ++t) {
            // R 연산
            if (R >= C) {
                hm = new HashMap[R];
                for (int r = 0; r < R; ++r) {
                    hm[r] = new HashMap<>();
                }

                int longest = 0;

                for (int r = 0; r < R; ++r) {
                    for (int c = 0; c < C; ++c) {
                        int key = map[r][c];

                        if (key == 0)
                            continue;

                        if (hm[r].containsKey(key)) {
                            int count = hm[r].get(key);
                            ++count;
                            hm[r].put(key, count);
                        } else {
                            hm[r].put(key, 1);
                        }
                    }

                    longest = Math.max(longest, hm[r].size() * 2);
                }

                oldC = C;
                // '행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.'
                // 이 규칙에 따라서, map의 크기가 100을 넘어가지 않도록 min()을 사용함
                C = Math.min(longest, 100);

                // oldC, C 중 큰 값을 찾는다.
                // 길이가 늘어난 부분중에, 입력할 값이 없는 경우에는 0을 채우기 위해서임
                maxC = Math.max(C, oldC);

                for (int r = 0; r < R; ++r) {
                    List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(hm[r].entrySet());
                    entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            if (o1.getValue() == o2.getValue()) {
                                return o1.getKey().compareTo(o2.getKey());
                            }

                            return o1.getValue().compareTo(o2.getValue());
                        }
                    });

                    for (int c = 0; c < maxC; ++c) {
                        if (entryList.size() * 2 > c) {
                            // c값을 entryList의 index로 변환하기 위해서, 나누기 2하고
                            Map.Entry<Integer, Integer> entry = entryList.get(c / 2);

                            // c가 짝수일 때, 숫자의 값을 map에 assign
                            if ((c & 1) == 0) {
                                map[r][c] = entry.getKey();
                            } else { // c 가 홀수일 때는, 숫자의 갯수를 map에 assign
                                map[r][c] = entry.getValue();
                            }
                        } else {
                            map[r][c] = 0;
                            map[r][c] = 0;
                        }
                    }
                }
            }
            // C 연산
            else {
                hm = new HashMap[C];
                for (int c = 0; c < C; ++c) {
                    hm[c] = new HashMap<>();
                }

                int longest = 0;

                for (int c = 0; c < C; ++c) {
                    for (int r = 0; r < R; ++r) {
                        int key = map[r][c];

                        if (key == 0)
                            continue;

                        if (hm[c].containsKey(key)) {
                            int count = hm[c].get(key);
                            ++count;
                            hm[c].put(key, count);
                        } else {
                            hm[c].put(key, 1);
                        }
                    }

                    longest = Math.max(longest, hm[c].size() * 2);
                }

                oldR = R;
                R = Math.min(longest, 100);
                maxR = Math.max(R, oldR);

                for (int c = 0; c < C; ++c) {
                    List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(hm[c].entrySet());
                    entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            if (o1.getValue() == o2.getValue()) {
                                return o1.getKey().compareTo(o2.getKey());
                            }

                            return o1.getValue().compareTo(o2.getValue());
                        }
                    });

                    for (int r = 0; r < maxR; ++r) {
                        if (entryList.size() * 2 > r) {
                            Map.Entry<Integer, Integer> entry = entryList.get(r / 2);

                            if ((r & 1) == 0) {
                                map[r][c] = entry.getKey();
                            } else {
                                map[r][c] = entry.getValue();
                            }
                        } else {
                            map[r][c] = 0;
                            map[r][c] = 0;
                        }
                    }
                }
            }

            if (map[ansR][ansC] == K) {
                bw.write(String.valueOf(t));
                bw.newLine();
                bw.close();
                System.exit(0);
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}