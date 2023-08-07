import java.util.HashSet;
/**
 * 202. Happy Number / LeetCode
 * 문제링크 : https://leetcode.com/problems/happy-number/
 * 제출링크 : https://leetcode.com/submissions/detail/320569584/
 */
public class Main {
    class Solution {
        public boolean isHappy(int n) {
            int v = n;
            int r = 0;
            HashSet<Integer> hs = new HashSet<>();

            while(!hs.contains(v)) {
                hs.add(v);

                while(v > 0) {
                    int a = v % 10;
                    v = v / 10;

                    r += (a*a);
                }

                if(1 == r)
                    return true;

                v = r;
                r = 0;
            }

            return false;
        }
    }

    public void solve() {
        Solution sln = new Solution();
        int in = 19;
        boolean r = sln.isHappy(in);
        System.out.println(r);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
