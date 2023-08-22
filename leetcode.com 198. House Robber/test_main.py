from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_rob(self):
        sol = Solution()
        self.assertEqual(4, sol.rob([1,2,3,1]))


    def test2_rob(self):
        sol = Solution()
        self.assertEqual(12, sol.rob([2,7,9,3,1]))
