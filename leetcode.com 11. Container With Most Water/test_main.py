from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_max_area(self):
        sol = Solution()
        self.assertEqual(49, sol.maxArea([1,8,6,2,5,4,8,3,7]))

    def test2_max_area(self):
        sol = Solution()
        self.assertEqual(1, sol.maxArea([1, 1]))
