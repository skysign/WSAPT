from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test4(self):
        sol = Solution()
        self.assertEqual(0, sol.lastStoneWeight([2,2]))
