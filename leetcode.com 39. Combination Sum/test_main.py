from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_combination_sum(self):
        sol = Solution()
        self.assertEqual([[2,2,3],[7]], sol.combinationSum([2,3,6,7], 7))

    def test2_combination_sum(self):
        sol = Solution()
        self.assertEqual([[2,2,2,2],[2,3,3],[3,5]], sol.combinationSum([2,3,5], 8))

    def test3_combination_sum(self):
        sol = Solution()
        self.assertEqual([], sol.combinationSum([2], 1))

    def test41_combination_sum(self):
        sol = Solution()
        self.assertEqual([[3,3,5],[3,8]], sol.combinationSum([3,5,8], 11))

    def test86_combination_sum(self):
        sol = Solution()
        self.assertEqual([[7, 7, 2, 2],[7,3,3,3,2],[7,3,2,2,2,2],[3,3,3,3,3,3],[3,3,3,3,2,2,2],[3,3,2,2,2,2,2,2],[2,2,2,2,2,2,2,2,2]], sol.combinationSum([7,3,2], 18))
