from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_find_order(self):
        sol = Solution()
        self.assertEqual([0,1], sol.findOrder(2, [[1,0]]))

    def test2_find_order(self):
        sol = Solution()
        self.assertEqual([0,2,1,3], sol.findOrder(4, [[1,0],[2,0],[3,1],[3,2]]))

    def test2_find_order(self):
        sol = Solution()
        self.assertEqual([0], sol.findOrder(1, []))
