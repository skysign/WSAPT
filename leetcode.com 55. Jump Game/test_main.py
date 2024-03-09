from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_can_jump(self):
        sol = Solution()
        self.assertEqual(True, sol.canJump([2,3,1,1,4]))

    def test2_can_jump(self):
        sol = Solution()
        self.assertEqual(False, sol.canJump([3,2,1,0,4]))
