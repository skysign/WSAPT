from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_subsets_with_dup(self):
        sol = Solution()
        self.assertEqual([[],[1],[1,2],[1,2,2],[2],[2,2]],
                         sol.subsetsWithDup([1,2,2]))

    def test2_subsets_with_dup(self):
        sol = Solution()
        self.assertEqual([[],[0]],
                         sol.subsetsWithDup([0]))
