from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_calc_equation(self):
        sln = Solution()
        self.assertEqual(
            [6.00000, 0.50000, -1.00000, 1.00000, -1.00000],
            sln.calcEquation(
                [["a", "b"], ["b", "c"]], [2.0, 3.0], [["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"]]
            )
        )

    def test2_calc_equation(self):
        sln = Solution()
        self.assertEqual(
            [3.75000, 0.40000, 5.00000, 0.20000],
            sln.calcEquation(
                equations=[["a", "b"], ["b", "c"], ["bc", "cd"]], values=[1.5, 2.5, 5.0], queries=[["a", "c"], ["c", "b"], ["bc", "cd"], ["cd", "bc"]]
            )
        )

    def test3_calc_equation(self):
        sln = Solution()
        self.assertEqual(
            [0.50000, 2.00000, -1.00000, -1.00000],
            sln.calcEquation(
                equations=[["a", "b"]], values=[0.5], queries=[["a", "b"], ["b", "a"], ["a", "c"], ["x", "y"]]
            )
        )

    def test18_calc_equation(self):
        sln = Solution()
        self.assertEqual(
            [360.00000, 0.008333333333333333, 20.00000, 1.00000, -1.00000, -1.00000],
            sln.calcEquation(
                equations=[["x1", "x2"], ["x2", "x3"], ["x3", "x4"], ["x4", "x5"]],
                values=[3.0, 4.0, 5.0, 6.0],
                queries=[["x1", "x5"], ["x5", "x2"], ["x2", "x4"], ["x2", "x2"], ["x2", "x9"], ["x9", "x9"]]
            )
        )
