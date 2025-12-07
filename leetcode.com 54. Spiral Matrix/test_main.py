from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test_spiral_order(self):
        sln = Solution()
        self.assertEqual([1,2,3,6,9,8,7,4,5], sln.spiralOrder(matrix = [[1,2,3],[4,5,6],[7,8,9]]))
