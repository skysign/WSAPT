from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test_can_jump(self):
        sln = Solution()
        self.assertEqual(True, sln.canJump([2,3,1,1,4]))

    def test_can_jump(self):
        sln = Solution()
        self.assertEqual(False, sln.canJump([3,2,1,0,4]))
