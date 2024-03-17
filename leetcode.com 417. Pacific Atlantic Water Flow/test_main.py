from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_pacific_atlantic(self):
        sol = Solution()
        self.assertEqual([[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]],\
                         sol.pacificAtlantic([[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]))

    def test2_pacific_atlantic(self):
        sol = Solution()
        self.assertEqual([[0,0]], sol.pacificAtlantic([[1]]))
