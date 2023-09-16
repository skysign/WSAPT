from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_find_min(self):
        sol = Solution()
        self.assertEqual(1, sol.findMin([3,4,5,1,2]))

    def test2_find_min(self):
        sol = Solution()
        self.assertEqual(0, sol.findMin([4,5,6,7,0,1,2]))

    def test3_find_min(self):
        sol = Solution()
        self.assertEqual(11, sol.findMin([11,13,15,17]))

    def test4_find_min(self):
        sol = Solution()
        self.assertEqual(1, sol.findMin([1,2]))

    def test6_find_min(self):
        sol = Solution()
        self.assertEqual(1, sol.findMin([1, 2, 3]))

    def test120_find_min(self):
        sol = Solution()
        self.assertEqual(1, sol.findMin([3, 1, 2]))

    def testx_find_min(self):
        sol = Solution()
        self.assertEqual(1, sol.findMin([2, 1]))
