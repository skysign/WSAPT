from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_search_matrix(self):
        sol = Solution()
        self.assertEqual(True, sol.searchMatrix([[1,3,5,7],[10,11,16,20],[23,30,34,60]], 3))

    def test2_search_matrix(self):
        sol = Solution()
        self.assertEqual(False, sol.searchMatrix([[1,3,5,7],[10,11,16,20],[23,30,34,60]], 13))
