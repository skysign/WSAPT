from unittest import TestCase
from main import Solution, ListNode


class TestSolution(TestCase):
    def test1_reverse_between(self):
        sln = Solution()
        node1 = ListNode(1, None)
        node2 = ListNode(2, None)
        node3 = ListNode(3, None)
        node4 = ListNode(4, None)
        node5 = ListNode(5, None)
        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        self.assertEqual(node1, sln.reverseBetween(node1, 2, 4))

    def test2_reverse_between(self):
        sln = Solution()
        head = ListNode(5, None)
        self.assertEqual(head, sln.reverseBetween(head, 1, 1))
