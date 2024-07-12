from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_num_distinct(self):
        sol = Solution()
        self.assertEqual(3, sol.numDistinct(s="rabbbit", t="rabbit"))

    def test2_num_distinct(self):
        sol = Solution()
        self.assertEqual(5, sol.numDistinct(s="babgbag", t="bag"))
