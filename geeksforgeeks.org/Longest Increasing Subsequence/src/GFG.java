import java.util.Scanner;

public class GFG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while(T-->0) {
            int N = sc.nextInt();
            int[] a = new int[N];

            for(int i=0; i<N; ++i) {
                a[i] = sc.nextInt();
            }

            int r = LIS(a, N);
            System.out.println(r);
        }
    }

    public static int LIS(int[] a, int N) {
        int[] lis = new int[N];

        for(int i=0; i<N; ++i) {
            lis[i] = 1;
            int maxLisLTi = 0;
            for(int j=0; j<i; ++j) {
                if(a[j] < a[i]) {
                    maxLisLTi = Math.max(maxLisLTi, lis[j]);
                }
            }

            lis[i] += maxLisLTi;
        }

        int r = lis[0];
        for(int i=0; i<N; ++i)
            r = Math.max(r, lis[i]);

        return r;
    }
}
