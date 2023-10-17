from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_trap(self):
        sol = Solution()
        self.assertEqual(6, sol.trap([0,1,0,2,1,0,1,3,2,1,2,1]))

    def test2_trap(self):
        sol = Solution()
        self.assertEqual(9, sol.trap([4,2,0,3,2,5]))

    def test4_trap(self):
        sol = Solution()
        self.assertEqual(0, sol.trap([0, 2, 0]))

    def test145_trap(self):
        sol = Solution()
        self.assertEqual(1, sol.trap([4,2,3]))

    def test160_trap(self):
        sol = Solution()
        self.assertEqual(23, sol.trap([5,5,1,7,1,1,5,2,7,6]))

    def test169_trap(self):
        sol = Solution()
        self.assertEqual(83, sol.trap([6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3]))

    def test263_trap(self):
        sol = Solution()
        self.assertEqual(23, sol.trap([0,5,6,4,6,1,0,0,2,7]))
