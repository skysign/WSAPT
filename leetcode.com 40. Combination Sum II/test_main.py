from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_combination_sum2(self):
        sol = Solution()
        self.assertEqual(
            [
                [1, 1, 6],
                [1, 2, 5],
                [1, 7],
                [2, 6]
            ],
            sol.combinationSum2([10,1,2,7,6,1,5], 8)
        )

    def test2_combination_sum2(self):
        sol = Solution()
        self.assertEqual(
            [
                [1, 2, 2],
                [5]
            ],
            sol.combinationSum2([2,5,2,1,2], 5)
        )

    def testxx_combination_sum2(self):
        sol = Solution()
        self.assertEqual(
            [[1,1,2,2],[1,1,4],[1,2,3],
             [2,2,2],
             [2,4]],
            sol.combinationSum2([4,4,2,1,4,2,2,1,3], 6)
        )

    def test23_combination_sum2(self):
        sol = Solution()
        self.assertEqual(
            [],
            sol.combinationSum2([1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], 27)
        )