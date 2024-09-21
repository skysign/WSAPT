from typing import List, Dict


class Solution:
    def maxCoins(self, nums: List[int]) -> int:
        nums = [1] + nums + [1]
        l = len(nums)
        dp = [[-1] * l for _ in range(l)]

        # DP Top down
        def dp_top_down(idx_left, idx_right):
            if idx_right - idx_left < 0:
                return 0

            if dp[idx_left][idx_right] > -1:
                return dp[idx_left][idx_right]

            coins_mx = 0

            for idx in range(idx_left, idx_right + 1):
                # idx 가 가리키는 풍선을 터뜨렸을 때, 얻게 되는 코인
                coins_idx = nums[idx_left - 1] * nums[idx] * nums[idx_right + 1]
                # idx 왼쪽 편
                coins_left = dp_top_down(idx_left, idx -1)
                # idx 오른쪽 편
                coins_right = dp_top_down(idx + 1, idx_right)

                coins_mx = max(coins_mx, coins_idx + coins_left + coins_right)

            dp[idx_left][idx_right] = coins_mx
            return dp[idx_left][idx_right]

        return dp_top_down(1, len(nums) - 2)

    # Naive DP
    # def maxCoins(self, nums: List[int]) -> int:
    #     memo = {}
    #     nums = [1] + nums + [1]
    #
    #     def dp(nums: List[int], memo: Dict):
    #         if tuple(nums) in memo.keys():
    #             return memo[tuple(nums)]
    #
    #         if len(nums) == 3:
    #             memo[tuple(nums)] = nums[0] * nums[1] * nums[2]
    #
    #         coins_mx = 0
    #
    #         for i in range(1, len(nums) - 1):
    #             coins_local = nums[i - 1] * nums[i] * nums[i + 1]
    #             coins_local += dp(nums[:i] + nums[i + 1:], memo)
    #             coins_mx = max(coins_mx, coins_local)
    #
    #         memo[tuple(nums)] = coins_mx
    #
    #         return coins_mx
    #
    #     return dp(nums, memo)
