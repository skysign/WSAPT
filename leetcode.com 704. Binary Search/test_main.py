from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_search(self):
        sol = Solution()
        self.assertEqual(4, sol.search([-1,0,3,5,9,12], 9))

    def test2_search(self):
        sol = Solution()
        self.assertEqual(-1, sol.search([-1,0,3,5,9,12], 2))
