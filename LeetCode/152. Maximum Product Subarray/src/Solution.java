public class Solution {
    public int maxProduct(int[] nums) {
        int r = nums[0];
        int[] v = new int[nums.length];
        int N = v.length;

        for(int i=0; i<v.length; ++i)
            v[i] = 1;

        for(int lengthOfSubarray = 1;
            lengthOfSubarray <= N;
            ++lengthOfSubarray) {

            for(int i=lengthOfSubarray-1, vi=0;
                i<N;
                ++i, ++vi) {
                v[vi] *= nums[i];
                r = Math.max(r, v[vi]);
            }
        }

        return r;
    }

    public static void main(String[] args) {
	// write your code here
    }
}
