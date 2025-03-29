from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test_find_order(self):
        sln = Solution()
        self.assertEqual([0,1], sln.findOrder(2, [[1,0]]))
