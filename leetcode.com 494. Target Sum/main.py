from typing import List

class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        depth_dp = {}
        plus_count = self.rec('+', 0, nums, 0, target, depth_dp)
        minus_count = self.rec('-', 0, nums, 0, target, depth_dp)

        return plus_count + minus_count

    def rec(self, plus_minus, depth, nums, prev_sum, target, depth_dp):
        if depth in depth_dp.keys():
            current_sum = prev_sum + nums[depth] if plus_minus == '+' else prev_sum - nums[depth]
            if current_sum in depth_dp[depth].keys():
                return depth_dp[depth][current_sum]['count']

        if depth == len(nums) -1:
            current_sum = prev_sum + nums[depth] if plus_minus == '+' else prev_sum - nums[depth]

            # depth에 처음 방문 해서, depth에 해당하는 dictionary를 만들어 줘야함
            depth_dp[depth] = depth_dp[depth] if depth in depth_dp.keys() else {}
            # depth의 current_sum에 처음 방문 해서, depth의 current_SUM에 해당하는 dictionary를 만들어 줘야함
            depth_dp[depth][current_sum] = depth_dp[depth][current_sum] if current_sum in depth_dp[depth].keys() else { 'count': 0}

            if current_sum == target:
                depth_dp[depth][current_sum]['count'] = 1
            else:
                depth_dp[depth][current_sum]['count'] = 0

            return depth_dp[depth][current_sum]['count']

        current_sum = prev_sum + nums[depth] if plus_minus == '+' else prev_sum - nums[depth]

        plus_count = self.rec('+', depth +1, nums, current_sum, target, depth_dp)
        minus_count = self.rec('-', depth +1, nums, current_sum, target, depth_dp)

        # depth에 처음 방문 해서, depth에 해당하는 dictionary를 만들어 줘야함
        depth_dp[depth] = depth_dp[depth] if depth in depth_dp.keys() else {}
        # depth의 current_sum에 처음 방문 해서, depth의 current_SUM에 해당하는 dictionary를 만들어 줘야함
        depth_dp[depth][current_sum] = depth_dp[depth][current_sum] if current_sum in depth_dp[depth].keys() else {'count': 0}
        depth_dp[depth][current_sum]['count'] = plus_count + minus_count

        return depth_dp[depth][current_sum]['count']
