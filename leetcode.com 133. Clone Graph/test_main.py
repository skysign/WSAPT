from unittest import TestCase
from main import Solution, Node

class TestSolution(TestCase):
    def test_clone_graph(self):
        sol = Solution()
        sol.cloneGraph(None)
