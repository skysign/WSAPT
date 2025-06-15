from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_is_interleave(self):
        sln = Solution()
        self.assertEqual(True, sln.isInterleave(s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"))
