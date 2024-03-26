from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_jump(self):
        sol = Solution()
        self.assertEqual(2, sol.jump([2,3,1,1,4]))

    def test2_jump(self):
        sol = Solution()
        self.assertEqual(2, sol.jump([2,3,0,1,4]))

    def test4_jump(self):
        sol = Solution()
        self.assertEqual(1, sol.jump([1,2]))
