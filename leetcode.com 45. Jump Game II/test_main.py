from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_jump(self):
        sol = Solution()
        self.assertEqual(2, sol.jump([2,3,1,1,4]))

    def test2_jump(self):
        sol = Solution()
        self.assertEqual(2, sol.jump([2,3,0,1,4]))

    def test40_jump(self):
        sol = Solution()
        self.assertEqual(3, sol.jump([1,2,1,1,1]))

    def test58_jump(self):
        sol = Solution()
        self.assertEqual(2, sol.jump([7,0,9,6,9,6,1,7,9,0,1,2,9,0,3]))

    def testxx_jump(self):
        sol = Solution()
        self.assertEqual(4, sol.jump([1,1,1,1,0]))

