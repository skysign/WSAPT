import java.util.Scanner;

public class Main {
    public static boolean[][] visited;

    public static double sushi(double[][] dp, int i, int j, int Z, int N) {
        if(i <=0 && j <= 0) {
            return 0;
        }

        if(visited[i][j]) {
            return dp[i][j];
        }

        visited[i][j] = true;

        dp[i][j] = (double)N/(double)(i+j);
        double r1 = 0, r2 = 0;
        double div = 0;
        if(i > 0) {
             r1 = sushi(dp, i-1, j, 0, N);
             div++;
        }
        if(j > 0) {
            r2 = sushi(dp, i+1, j-1, 0, N);
            div++;
        }
        dp[i][j] = dp[i][j] + ((r1 + r2) / div);
//        // i < x
//        // j < y
//        for(int i=0; i<X; ++i) {
//            for(int j=0; j<Y; ++j) {
//
//            }
//        }

        return dp[i][j];
    }

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int N = sc.nextInt();
        double[][] dp;

        int x = 1, y = 1;

        for(int i=0; i<N; ++i) {
            int t = sc.nextInt();
            if(1 == t) {
                x++;
            }
            if(2 == t) {
                y++;
            }
        }

        dp = new double[x+1][y];
        visited = new boolean[x+1][y];

        double r = sushi(dp, x-1, y-1, 0, N);
        System.out.printf("%.10f\n", r);
    }
}
