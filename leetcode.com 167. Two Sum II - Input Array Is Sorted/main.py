from typing import List

# leetcode.com 167. Two Sum II - Input Array Is Sorted
#
# 유튜브 문제 풀이: https://youtu.be/5Nu8kzohGDM?si=vQB2YD-V27iGuuBH
#
# 참고소스: https://bit.ly/45kKBio
#
# 문제 링크: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

class Solution:
    def twoSum(self, dt: List[int], target: int) -> List[int]:
        sum = -2001

        pl = 0
        pr = len(dt) -1

        while pl < pr:
            sum = dt[pl] + dt[pr]

            if sum == target:
                return [pl +1, pr +1]
            else:
                if sum > target:
                    pr -= 1
                if sum < target:
                    pl += 1

        return []