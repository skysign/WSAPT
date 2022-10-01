import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[][][] map;
    static int BLUE = 0;
    static int GREN = 1;

    static int TYPE = 0;
    static int ROW = 1;
    static int COL = 2;

    static int TYPE1 = 1;
    static int TYPE2 = 2;
    static int TYPE3 = 3;

    int[][] mapBlock;

    ArrayList<int[]> alBlock;
    int ans;
    String[] strs;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());

        alBlock = new ArrayList<>();

        for (int idx = 0; idx < N; ++idx) {
            strs = br.readLine().split(" ");
            alBlock.add(
                    new int[]{
                            Integer.parseInt(strs[0]),
                            Integer.parseInt(strs[1]),
                            Integer.parseInt(strs[2])
                    });
        }

        map = new int[2][6 + 1][4];
        mapBlock = new int[2][4];

        // map의 제일 마지막 row는 모두 블럭에 찬걸로 가정함
        // 블럭의 이동을 쉽게 구현하기 위함
        for (int col = 0; col < 4; ++col) {
            map[BLUE][6][col] = 1;
            map[GREN][6][col] = 1;
        }

        for (int[] block : alBlock) {
            putBlock(map[GREN], block);

            int[] blockForblue = covertBlock(block);
            putBlock(map[BLUE], blockForblue);
        }


        int remainedBlock = 0;

        for (int r = 2; r < 6; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (map[BLUE][r][c] == 1)
                    ++remainedBlock;

                if (map[GREN][r][c] == 1)
                    ++remainedBlock;
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.write(String.valueOf(remainedBlock));
        bw.newLine();
        bw.close();
    }

    public void putBlock(int[][] map, int[] block) {
        placeBlockToMove(block, mapBlock);
        int rowHit = 0;

        // block을 한 row씩 아래로 움직이면서,
        // 다른 블럭과 부딪히는지 확인함
        for (int row = 0; row < map.length; ++row) {
            boolean b = checkHit(row, map, mapBlock);
            if (b) {
                rowHit = row;
                break;
            }
        }

        --rowHit;
        placeBlockOnMap(rowHit, map, mapBlock);

        for (int row = 5; row > 1; --row) {
            boolean b = checkRow(row, map);
            if (b) {
                removeRow(row, map);
                // 복수의 row가 한번에 삭제 될 수 있음
                // 이문제에서는 2줄이 한번에 사라질 수 있음
                // 따라서, 한번 체크한 줄을 다시한번 체크 해야함
                ++row;
                ++ans;
            }
        }

        // 연한색 공간 에 블럭이 있는지 확인
        boolean yeon = checkYeon(map);
        if (yeon) {
            removeRow(5, map);
        }

        // 연한색 공간이 2줄이기 때문에, 2번 체크함
        yeon = checkYeon(map);
        if (yeon) {
            removeRow(5, map);
        }

        Arrays.fill(map[0], 0);
        Arrays.fill(map[1], 0);
    }

    public boolean checkYeon(int[][] map) {
        for (int row = 0; row < 2; ++row) {
            for (int col = 0; col < map[0].length; ++col) {
                if (map[row][col] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkRow(int row, int[][] map) {
        for (int col = 0; col < map[0].length; ++col) {
            if (map[row][col] == 0)
                return false;
        }

        return true;
    }

    public void removeRow(int row, int[][] map) {
        for (int r = row; r > 0; --r) {
            System.arraycopy(map[r - 1], 0, map[r], 0, map[r - 1].length);
        }
    }

    public void placeBlockOnMap(int row, int[][] map, int[][] mapBlock) {
        for (int rowBlock = 0; rowBlock < mapBlock.length; ++rowBlock) {
            for (int colBlock = 0; colBlock < mapBlock[0].length; ++colBlock) {
                if (mapBlock[rowBlock][colBlock] == 1) {
                    map[row + rowBlock][colBlock] = 1;
                }
            }
        }
    }

    public boolean checkHit(int row, int[][] map, int[][] mapBlock) {
        for (int rowBlock = 0; rowBlock < mapBlock.length; ++rowBlock) {
            for (int colBlock = 0; colBlock < mapBlock[0].length; ++colBlock) {
                if ((mapBlock[rowBlock][colBlock] == 1)
                        && (map[row + rowBlock][colBlock] == 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void placeBlockToMove(int[] block, int[][] map) {
        int t = block[TYPE];
        int r = block[ROW];
        int c = block[COL];

        fill2D(map, 0);

        map[0][c] = 1;

        if (t == TYPE2) {
            map[0][c + 1] = 1;
        } else if (t == TYPE3) {
            map[1][c] = 1;
        }
    }

    public int[] covertBlock(int[] block) {
        int[] rtn = new int[3];

        // 좌표변환 예제
        // (ROW 1, COL 0) for 녹색
        // (ROW 0, COL (4 - 1) for 파란색
        int rowForBlue = block[COL];
        int colForBlue = 3 - block[ROW];

        int typForBlue = block[TYPE];
        if (typForBlue == TYPE2)
            typForBlue = TYPE3;
        else if (typForBlue == TYPE3) {
            typForBlue = TYPE2;
            --colForBlue;
        }

        rtn[ROW] = rowForBlue;
        rtn[COL] = colForBlue;
        rtn[TYPE] = typForBlue;

        return rtn;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }
}