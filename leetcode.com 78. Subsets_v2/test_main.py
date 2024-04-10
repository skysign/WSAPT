from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_subsets(self):
        sol = Solution()
        print(sol.subsets([1,2,3]))
