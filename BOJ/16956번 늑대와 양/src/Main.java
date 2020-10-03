import java.util.Scanner;
/**
 * BOJ 16956번 늑대와 양
 *
 * 유튜브 문제 풀이
 * https://youtu.be/ZFdfi0-SV7o
 *
 * 문제링크 : https://www.acmicpc.net/problem/16956
 *
 * 자바소스 : https://bit.ly/2EZmsIc
 */
public class Main {
    public int R, C;
    public char[][] map;

    // 4 ways
    public int[] d4i = new int[]{-1, 1, 0, 0};
    public int[] d4j = new int[]{0, 0, -1, 1};

    public void solve() {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();

        map = new char[R][C];
        for(int i=0; i<R; ++i) {
            String strLine = sc.nextLine();
            for(int j=0; j<C; ++j) {
                map[i][j] = strLine.charAt(j);
            }
        }

        int rlt = 1;

        for(int i=0; (i < R) && (rlt == 1); ++i) {
            for(int j=0; (j < C) && (rlt == 1); ++j) {
                int sY = i;
                int sX = j;

                // 양 찾기
                if(map[sY][sX] == 'S') {
                    for(int idx = 0; idx < d4i.length; ++idx) {
                        int dY = sY + d4i[idx];
                        int dX = sX + d4j[idx];

                        if(IsIn(dY, dX)) {
                            if(map[dY][dX] == '.') {
                                map[dY][dX] = 'D';
                            }
                            else if(map[dY][dX] == 'W') {
                                rlt = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(rlt == 0) {
            System.out.print(rlt);
        }
        else {
            System.out.println(rlt);

            for(int i=0; i<R; ++i) {
                for(int j=0; j<C; ++j) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }

    public boolean IsIn(int y, int x) {
        if((0<=y && y<R) && (0<=x && x<C))
            return true;

        return false;
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
