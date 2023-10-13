from unittest import TestCase
from main import Solution

# leetcode.com 167. Two Sum II - Input Array Is Sorted
#
# 유튜브 문제 풀이: https://youtu.be/5Nu8kzohGDM?si=vQB2YD-V27iGuuBH
#
# 파이썬 소스: https://bit.ly/45kKBio
#
# 문제 링크: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

class TestSolution(TestCase):
    def test1_two_sum(self):
        sol = Solution()
        self.assertEqual([1, 2], sol.twoSum([2,7,11,15], 9))

    def test2_two_sum(self):
        sol = Solution()
        self.assertEqual([1, 3], sol.twoSum([2,3,4], 6))

    def test1_two_sum(self):
        sol = Solution()
        self.assertEqual([1, 2], sol.twoSum([-1,0], -1))


