from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_trap(self):
        sol = Solution()
        self.assertEqual(6, sol.trap([0,1,0,2,1,0,1,3,2,1,2,1]))

    def test2_trap(self):
        sol = Solution()
        self.assertEqual(9, sol.trap([4,2,0,3,2,5]))

    def test145_trap(self):
        sol = Solution()
        self.assertEqual(1, sol.trap([4,2,3]))
