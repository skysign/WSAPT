from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1(self):
        sol = Solution()
        self.assertEqual(5, sol.findKthLargest([3,2,1,5,6,4], 2))
