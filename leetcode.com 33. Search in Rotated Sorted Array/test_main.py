from unittest import TestCase
from main import Solution

# leetcode.com 33. Search in Rotated Sorted Array
#
# 유튜브 문제 풀이: https://youtu.be/hAOMTq6XQj4?si=nCVWEwu3TvhyWfN8
#
# 파이썬 소스: https://bit.ly/48EucIm
#
# 문제 링크: https://bit.ly/46gLUAg

class TestSolution(TestCase):
    def test1_search(self):
        sol = Solution()
        self.assertEqual(4, sol.search([4,5,6,7,0,1,2], 0))

    def test2_search(self):
        sol = Solution()
        self.assertEqual(-1, sol.search([4,5,6,7,0,1,2], 3))

    def test3_search(self):
        sol = Solution()
        self.assertEqual(-1, sol.search([1], 0))

    def test180_search(self):
        sol = Solution()
        self.assertEqual(4, sol.search([4,5,6,7,8,1,2,3], 8))

    def test182_search(self):
        sol = Solution()
        self.assertEqual(1, sol.search([5,1,2,3,4], 1))