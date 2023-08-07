/**
 * 53. Maximum Subarray / LeetCode
 * 문제링크 : https://leetcode.com/problems/maximum-subarray/
 * 제출링크 : https://leetcode.com/submissions/detail/320577760/
 */
public class Main {
    class Solution {
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];

            for(int i=1; i<nums.length; ++i) {
                dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
            }

            int r = Integer.MIN_VALUE;
            for(int d: dp) {
                r = Math.max(r, d);
            }

            return r;
        }
    }

    public void solve() {
        Solution sln = new Solution();
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};

        int r = sln.maxSubArray(nums);
        System.out.println(r);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
