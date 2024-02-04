from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test2(self):
        sol = Solution()
        self.assertEqual([[3,3],[-2,4]], sol.kClosest([[3,3],[5,-1],[-2,4]], 2))
