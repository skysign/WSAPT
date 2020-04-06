/**
 * 283. Move Zeroes / LeetCode
 * 문제링크 : https://leetcode.com/problems/move-zeroes/
 * 제출링크 : https://leetcode.com/submissions/detail/320637228/
 */
public class Main {
    class Solution {
        public void moveZeroes(int[] nums) {
            int nZero = 0;
            for(int i=0; i<nums.length; ++i) {
                if(0 == nums[i])
                    nZero++;
                else
                    nums[i-nZero] = nums[i];
            }

            for(int i=nums.length-1; nZero>0; i--, nZero--)
                nums[i] = 0;
        }
    }

    public void solve() {
        Solution sln = new Solution();
        int[] inputs = new int[]{0,1,0,3,12};

        sln.moveZeroes(inputs);

        for(int input: inputs) {
            System.out.print(input);
            System.out.print(' ');
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
