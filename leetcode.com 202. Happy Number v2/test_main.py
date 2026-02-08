from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test0_is_happy(self):
        sln = Solution()
        self.assertEqual(True, sln.isHappy(19))
