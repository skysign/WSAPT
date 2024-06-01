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

    def test45_rob(self):
        sol = Solution()
        self.assertEqual(340, sol.rob([200, 3, 140, 20, 10]))

    def test52_rob(self):
        sol = Solution()
        self.assertEqual(41, sol.rob([1, 1, 3, 6, 7, 10, 7, 1, 8, 5, 9, 1, 4, 4, 3]))

    def test53_rob(self):
        sol = Solution()
        self.assertEqual(1, sol.rob([1, 1, 1]))

    def test55_rob(self):
        sol = Solution()
        self.assertEqual(3, sol.rob([1, 2, 1, 1]))

    def test74_rob(self):
        sol = Solution()
        self.assertEqual(16, sol.rob([1, 2, 3, 4, 5, 1, 2, 3, 4, 5]))
