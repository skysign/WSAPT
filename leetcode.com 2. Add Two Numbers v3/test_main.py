from unittest import TestCase
from main import Solution, ListNode


class TestSolution(TestCase):
    def test2_add_two_numbers(self):
        sln = Solution()
        r = sln.addTwoNumbers(ListNode(0), ListNode(0))
        self.assertEqual(0, r.val)
