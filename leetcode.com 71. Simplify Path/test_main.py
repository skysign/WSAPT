from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_simplify_path(self):
        sln = Solution()
        self.assertEqual('/home', sln.simplifyPath(path = "/home/"))
