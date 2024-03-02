from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_min_interval(self):
        sol = Solution()
        self.assertEqual([3,3,1,4], \
                         sol.minInterval([[1,4],[2,4],[3,6],[4,4]], [2,3,4,5]))

    def test2_min_interval(self):
        sol = Solution()
        self.assertEqual([2,-1,4,6], \
                         sol.minInterval([[2,3],[2,5],[1,8],[20,25]], [2,19,5,22]))
