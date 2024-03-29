from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_find_redundant_connection(self):
        sol = Solution()
        self.assertEqual([2, 3], sol.findRedundantConnection([[1,2],[1,3],[2,3]]))

    def test2_find_redundant_connection(self):
        sol = Solution()
        self.assertEqual([1, 4], sol.findRedundantConnection([[1,2],[2,3],[3,4],[1,4],[1,5]]))

    def test10_find_redundant_connection(self):
        sol = Solution()
        self.assertEqual([2, 5], sol.findRedundantConnection([[3,4],[1,2],[2,4],[3,5],[2,5]]))

    def test19_find_redundant_connection(self):
        sol = Solution()
        self.assertEqual([4, 5], sol.findRedundantConnection([[1,5],[3,4],[3,5],[4,5],[2,4]]))

    def test20_find_redundant_connection(self):
        sol = Solution()
        self.assertEqual([8,9], sol.findRedundantConnection([[3,7],[1,4],[2,8],[1,6],[7,9],[6,10],[1,7],[2,3],[8,9],[5,9]]))

    def testxx_find_redundant_connection(self):
        sol = Solution()
        self.assertEqual([4,10], sol.findRedundantConnection([[9,10],[5,8],[2,6],[1,5],[3,8],[4,9],[8,10],[4,10],[6,8],[7,9]]))