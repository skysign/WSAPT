from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_exist(self):
        sol = Solution()
        self.assertEqual(
            True, sol.exist(
                [["A", "B", "C", "E"], ["S", "F", "C", "S"], ["A", "D", "E", "E"]],
                "ABCCED"
            )
        )

    def test2_exist(self):
        sol = Solution()
        self.assertEqual(
            True, sol.exist(
                [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
                "SEE"
            )
        )

    def test3_exist(self):
        sol = Solution()
        self.assertEqual(
            False, sol.exist(
                [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]],
                "ABCB"
            )
        )