from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_longest_increasing_path(self):
        sol = Solution()
        self.assertEqual(4, sol.longestIncreasingPath([[9,9,4],[6,6,8],[2,1,1]]))

    def test2_longest_increasing_path(self):
        sol = Solution()
        self.assertEqual(4, sol.longestIncreasingPath([[3,4,5],[3,2,6],[2,2,1]]))

    def test3_longest_increasing_path(self):
        sol = Solution()
        self.assertEqual(1, sol.longestIncreasingPath([[1]]))
