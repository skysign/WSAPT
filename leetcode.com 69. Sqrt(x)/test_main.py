from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_my_sqrt(self):
        sln = Solution()
        self.assertEqual(2, sln.mySqrt(4))

    def test2_my_sqrt(self):
        sln = Solution()
        self.assertEqual(2, sln.mySqrt(8))
