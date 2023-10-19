from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_two_sum(self):
        sol = Solution()
        self.assertEqual([0, 1], sol.twoSum([2,7,11,15], 9))

    def test2_two_sum(self):
        sol = Solution()
        self.assertEqual([1, 2], sol.twoSum([3,2,4], 6))

    def test3_two_sum(self):
        sol = Solution()
        self.assertEqual([0, 1], sol.twoSum([3,3], 6))