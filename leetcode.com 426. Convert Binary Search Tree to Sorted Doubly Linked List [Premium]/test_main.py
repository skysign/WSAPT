from unittest import TestCase
from main import Solution, Node

class TestSolution(TestCase):
    def test1_tree_to_doubly_list(self):
        sol = Solution()
        node1 = Node(1)
        node3 = Node(3)
        node2 = Node(2, node1, node3)
        node5 = Node(5)
        node4 = Node(4, node2, node5)
        answer = sol.treeToDoublyList(node4)
        print(answer.val)
        self.assertEqual(0, 0)
