from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_jump(self):
        sln = Solution()
        self.assertEqual(2, sln.jump([2,3,1,1,4]))

    def test2_jump(self):
        sln = Solution()
        self.assertEqual(2, sln.jump([2,3,0,1,4]))
