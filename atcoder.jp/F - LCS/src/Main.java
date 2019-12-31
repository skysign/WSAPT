import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

// F - LCS / atcoder.jp
// 문제 링크 : https://atcoder.jp/contests/dp/tasks/dp_f
// 문제 해설 : https://jinpyo.kim/EducationalDP-solution
// 이번 풀이는 참고했던 문제해설과는 약간 다른게 풀어졌습니다.
// Submission : https://atcoder.jp/contests/dp/submissions/9250597

public class Main {
    static public char[] S;
    static public char[] T;

    public static void main(String[] args) {
        FastReader sc = new FastReader();
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        String s = sc.nextLine();
        String t = sc.nextLine();
        s = ' ' + s;
        t = ' ' + t;
        S = s.toCharArray();
        T = t.toCharArray();

        int[][] dp = new int[S.length][T.length];
        String r = LCS(dp, S, T);

//        d0(dp, S, T);

        pw.println(r);
        pw.close();
    }

    public static String LCS(int[][] dp, char[] S, char[] T) {
        for(int i=1; i<S.length; ++i) {
            for(int j=1; j<T.length; ++j) {
                // begin 로직을 풀어써서 구현한 부분
//                // 이부분이 앞에서 풀었던 DP 문제들과 달라지는 부분입니다.
//                // S에 속해있는 특정 문자가, T 에서 2회 이상 들어 있을 때,
//                // 증가하는 것을 막기 위해서, dp[i][j] 값을 dp[i-1][j] 와 dp[i][j-1]중에
//                // 큰 값으로 assign 을 합니다.
//                if(dp[i][j-1] < dp[i-1][j]) {
//                    dp[i][j] = dp[i-1][j];
////                    System.out.printf("from (%d, %d) to(%d,%d) \n", i-1, j, i, j);
//                }
//                else {
//                    dp[i][j] = dp[i][j-1];
////                    System.out.printf("from (%d, %d) to(%d,%d) \n", i, j-1, i, j);
//                }
//
//                // (dp[i][j] < dp[i-1][j-1] + 1)
//                // 이부분이 앞에서 풀었던 DP 문제들과 달라지는 부분입니다.
//                // S에 속해있는 특정 문자가, T 에서 2회 이상 들어 있다면,
//                // 위의 로직이 없는 경우에, dp[i][j] 값이 1더 증가 하게 됩니다.
//                if(S[i] == T[j] && (dp[i][j] < dp[i-1][j-1] + 1)) {
//                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + 1;
////                    System.out.printf("from (%d, %d) to(%d,%d) \n", i-1, j-1, i, j);
////                    System.out.printf(" %c \n", S[i]);
//                }
                // end 로직을 풀어써서 구현한 부분

                // begin 로직을 줄여서 구현한 부분
                dp[i][j] = (S[i] == T[j])? dp[i-1][j-1] +1: Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

//        d0(dp, S, T);
        // 스트링 객체를 새로 만들지 말고
        // char배열에 저장했다가,
        // 마지막에 스트링객체를 한번만 만드는 것으로, 속도를 약간 빠르게 할 수 있습니다.
        char[] r = new char[dp[S.length-1][T.length-1]];
//        String r = "";

        int i = S.length-1;
        int j = T.length-1;
        while(0<i && 0<j) {
            if(dp[i-1][j] != dp[i][j] && dp[i][j] != dp[i][j-1]) {
//                r = S[i] + r;
                r[dp[i][j]-1] = S[i];
//                System.out.println(r);
                i--;
                j--;
            }
            // 왼쪽(i-1), 위(j-1), 둘중에 큰 쪽으로 이동합니다.
            else if(dp[i-1][j] < dp[i][j-1]) {
                j--;
            }
            else {
                i--;
            }
        }

//        #1 backTrack 으로 푸는 것도 가능하지만,
//        시간이 올래 걸려서, 타임아웃되어서, 정답처리 되지 않습니다.
//        r = backTrack(dp, S.length-1, T.length-1);
//
//        return r;
        return new String(r);
    }

//    public static String backTrack(int[][] dp, int i, int j) {
//        if(!(0<i && 0<j))
//            return "";
//
//        if(dp[i-1][j] != dp[i][j] && dp[i][j] != dp[i][j-1]) {
////            System.out.printf("(%d,%d) %s\n", i, j, S[i]);
//            return backTrack(dp, i-1, j-1) +  S[i];
//        }
//
//        String r1 = backTrack(dp, i-1, j);
//        String r2 = backTrack(dp, i, j-1);
//
//        if(r1.length() > r2.length())
//            return r1;
//
//        return r2;
//    }

    public static void d0(int[][] dp, char[] S, char[] T) {
        System.out.print("      ");

        for(int j=0; j<dp[0].length; ++j){
            System.out.printf("%c%3d|", T[j], j);
        }
        System.out.println();

        for(int i=0; i<dp.length; ++i){
            System.out.printf("%c %3d|", S[i], i);
            for(int j=0; j<dp[0].length; ++j){
                System.out.printf(" %3d ", dp[i][j]);
            }
            System.out.println();
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
