from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_maximal_square(self):
        sln = Solution()
        self.assertEqual(4, sln.maximalSquare([["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]))

    def test2_maximal_square(self):
        sln = Solution()
        self.assertEqual(1, sln.maximalSquare([["0","1"],["1","0"]]))

    def test59_maximal_square(self):
        sln = Solution()
        self.assertEqual(1, sln.maximalSquare([["1"]]))
