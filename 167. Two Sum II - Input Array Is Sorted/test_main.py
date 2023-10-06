from unittest import TestCase
from main import Solution

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


