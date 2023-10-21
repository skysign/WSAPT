from typing import List
# leetcode.com 11. Container With Most Water
#
# 유튜브 문제 풀이: https://youtu.be/Nq0DV7dn7So?si=LjAW9kqp2s5uNNfP
#
# 파이썬 소스: https://bit.ly/3S0Lsly
#
# 문제 링크: https://leetcode.com/problems/container-with-most-water/
class Solution:
    def maxArea(self, heights: List[int]) -> int:
        plt = 0
        prt = len(heights) -1
        max_area = 0

        while plt < prt:
            max_area = max(max_area, (prt -plt) * min(heights[plt], heights[prt]))

            if heights[plt] < heights[prt]:
                plt += 1
            else:
                prt -= 1

        return max_area