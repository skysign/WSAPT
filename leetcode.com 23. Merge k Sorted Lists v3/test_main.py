from unittest import TestCase
from main import Solution, ListNode


class TestSolution(TestCase):
    def test0_merge_klists(self):
        n1 = ListNode(1, ListNode(2))
        n3 = ListNode(3, ListNode(4))
        sln = Solution()
        rtn = sln.mergeKLists([n1, n3])
        print(rtn)

    def test0_merge_klists(self):
        n1 = ListNode(1, ListNode(4, ListNode(5)))
        n2 = ListNode(1, ListNode(3, ListNode(4)))
        n3 = ListNode(2, ListNode(6))
        sln = Solution()
        rtn = sln.mergeKLists([n1, n2, n3])
        print(rtn)
