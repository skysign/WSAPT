public class Solution {
    public int climbStairs(int N) {
        if(N < 2) {
            return N;
        }

        int r[] = new int[N+1];
        r[0] = 1;
        r[1] = 1;

        for(int i=2; i<=N; ++i)
            r[i] = r[i-1] + r[i-2];

        return r[N];
    }

    public static void main(String[] args) {
	// write your code here
    }
}
