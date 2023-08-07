import java.util.HashSet;
/**
 * 136. Single Number / LeetCode
 * 문제링크 : https://leetcode.com/problems/single-number/
 * 제출링크 : https://leetcode.com/submissions/detail/320559907/
 */
public class Main {
    class Solution {
        public int singleNumber(int[] nums) {
            HashSet<Integer> hs = new HashSet<>();
            for(int n: nums) {
                if(hs.contains(n)) {
                    hs.remove(n);
                }
                else {
                    hs.add(n);
                }
            }

            Integer[] rs = new Integer[1];
            rs = hs.toArray(rs);

            return rs[0];
        }
    }

    public void solve() {
        int[] inputs;
//        inputs = new int[]{2,2,1};
        inputs = new int[]{4,1,2,1,2};

        Solution sln = new Solution();
        int r = sln.singleNumber(inputs);
        System.out.println(r);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
