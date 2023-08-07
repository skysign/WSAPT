# leetcode.com 416. Partition Equal Subset Sum
#
# 유튜브 문제 풀이: https://youtu.be/nLHNyrM-3VM
#
# 파이썬 소스: https://bit.ly/3Qw5C6a
#
# 문제 링크: https://leetcode.com/problems/partition-equal-subset-sum/

class Solution:
    def canPartition(self, dt: list[int]) -> bool:
        my_half = int(sum(dt) / 2)
        if sum(dt) % 2 == 1:
            return False

        dp = [False for _ in range(my_half +1)]
        dp[0] = True

        for num in dt:
            for idx in range(my_half -1, -1, -1):
                if dp[idx] and idx + num <= my_half:
                    dp[idx + num] = dp[idx]

                if dp[my_half]:
                    return True

        return False
