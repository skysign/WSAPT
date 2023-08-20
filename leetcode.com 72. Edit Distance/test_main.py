from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_min_distance(self):
        sol = Solution()
        self.assertEqual(3, sol.minDistance("horse", "ros"))
