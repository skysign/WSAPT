from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_rob(self):
        sol = Solution()
        self.assertEqual(3, sol.rob([2, 3, 2]))

    def test2_rob(self):
        sol = Solution()
        self.assertEqual(4, sol.rob([1, 2, 3, 1]))

    def test3_rob(self):
        sol = Solution()
        self.assertEqual(3, sol.rob([1, 2, 3]))

    def test53_rob(self):
        sol = Solution()
        self.assertEqual(3, sol.rob([1,1,1]))

    def test65_rob(self):
        sol = Solution()
        self.assertEqual(41, sol.rob([1,1,3,6,7,10,7,1,8,5,9,1,4,4,3]))
