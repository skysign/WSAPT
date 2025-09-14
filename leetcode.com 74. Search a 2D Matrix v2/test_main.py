from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test_search_matrix(self):
        sln = Solution()
        self.assertEqual(True, sln.searchMatrix(
            [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50]],
            11
        ))
