from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_two_sum(self):
        sln = Solution()
        self.assertEqual([1, 2], sln.twoSum(numbers = [2,7,11,15], target = 9))

    def test2_two_sum(self):
        sln = Solution()
        self.assertEqual([1,3], sln.twoSum(numbers = [2,3,4], target = 6))

    def test3_two_sum(self):
        sln = Solution()
        self.assertEqual([1,2], sln.twoSum(numbers = [-1,0], target = -1))
