from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_build_tree(self):
        sln = Solution()
        sln.buildTree(inorder = [9,3,15,20,7], postorder = [9,15,7,20,3])
