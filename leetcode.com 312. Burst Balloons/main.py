from typing import List
import sys


# N ** 3으로 작성된 코드
# def maxCoins(self, nums: List[int]) -> int:
#     n = len(nums)
#     nums = [1] + nums + [1]
#     dp = [[0] * (n + 2) for _ in range(n + 2)]
#
#     for i in range(n, 0, -1):
#         for j in range(i, n + 1):
#             for k in range(i, j + 1):
#                 dp[i][j] = max(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + nums[k] * nums[i - 1] * nums[j + 1])
#
#     return dp[1][n]

class Solution:
    def maxCoins(self, nums: List[int]) -> int:
        nums = [1] + nums + [1]
        l = len(nums)
        dp = [[-1] * l for _ in range(l)]

        def dp_d_c(i_left, i_right):
            if i_right - i_left < 0:
                return 0

            if dp[i_left][i_right] > -1:
                return dp[i_left][i_right]

            coins_mx = 0

            for i in range(i_left, i_right + 1):
                coins_i = nums[i_left - 1] * nums[i] * nums[i_right + 1]
                coins_lr = 0
                coins_lr += dp_d_c(i_left, i - 1)
                coins_lr += dp_d_c(i + 1, i_right)

                coins_mx = max(coins_mx, coins_i + coins_lr)

            dp[i_left][i_right] = coins_mx

            return dp[i_left][i_right]

        coins = dp_d_c(1, l - 2)
        return coins

# DP (Naive)
# def maxCoins(self, nums: List[int]) -> int:
#     memo: dict = {}
#     nums = [1] + nums + [1]
#     coins = self.dp(nums, memo)
#
#     return coins
#
# def dp(self, nums: List[int], memo: dict):
#     if tuple(nums) in memo.keys():
#         return memo[tuple(nums)]
#
#     if len(nums) == 3:
#         memo[tuple(nums)] = nums[0] * nums[1] * nums[2]
#         return memo[tuple(nums)]
#
#     coins = 0
#     mx = -sys.maxsize - 1
#
#     for i in range(1, len(nums) - 1):
#         coins = nums[i - 1] * nums[i] * nums[i + 1]
#         coins_2 = self.dp(nums[:i] + nums[i + 1:], memo)
#
#         mx = max(mx, coins + coins_2)
#
#     memo[tuple(nums)] = mx
#
#     return mx
