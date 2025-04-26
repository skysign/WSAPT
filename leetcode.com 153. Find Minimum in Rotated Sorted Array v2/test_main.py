from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_find_min(self):
        sln = Solution()
        self.assertEqual(1, sln.findMin([3, 4, 5, 1, 2]))

    def test2_find_min(self):
        sln = Solution()
        self.assertEqual(0, sln.findMin([4, 5, 6, 7, 0, 1, 2]))

    def test3_find_min(self):
        sln = Solution()
        self.assertEqual(11, sln.findMin([11, 13, 15, 17]))
