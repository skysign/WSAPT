from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_build_tree(self):
        sln = Solution()
        sln.buildTree(preorder = [3,9,20,15,7], inorder = [9,3,15,20,7])
