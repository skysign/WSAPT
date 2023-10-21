from unittest import TestCase
from main import Solution
# leetcode.com 11. Container With Most Water
#
# 유튜브 문제 풀이: https://youtu.be/Nq0DV7dn7So?si=LjAW9kqp2s5uNNfP
#
# 파이썬 소스: https://bit.ly/3S0Lsly
#
# 문제 링크: https://leetcode.com/problems/container-with-most-water/
class TestSolution(TestCase):
    def test1_max_area(self):
        sol = Solution()
        self.assertEqual(49, sol.maxArea([1,8,6,2,5,4,8,3,7]))

    def test2_max_area(self):
        sol = Solution()
        self.assertEqual(1, sol.maxArea([1, 1]))
