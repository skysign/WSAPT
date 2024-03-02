from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_merge(self):
        sol = Solution()
        self.assertEqual([[1,6],[8,10],[15,18]], sol.merge([[1,3],[2,6],[8,10],[15,18]]))

    def test2_merge(self):
        sol = Solution()
        self.assertEqual([[1,5]], sol.merge([[1,4],[4,5]]))
