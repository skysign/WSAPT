# Definition for singly-linked list.
from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        sum_head = ListNode((l1.val + l2.val) % 10)
        sum_current = sum_head
        current1 = l1.next
        current2 = l2.next
        carry = 1 if (l1.val + l2.val) >= 10 else 0

        while current1 or current2:
            if current1 and current2:
                tmp = ListNode((current1.val + current2.val + carry) % 10)
                carry = 1 if (current1.val + current2.val + carry) >= 10 else 0
                sum_current.next = tmp
                sum_current = sum_current.next

                current1 = current1.next
                current2 = current2.next
            else:
                if current1:
                    tmp = ListNode((current1.val + carry) % 10)
                    carry = 1 if (current1.val + carry) >= 10 else 0
                    sum_current.next = tmp
                    sum_current = sum_current.next

                    current1 = current1.next
                elif current2:
                    tmp = ListNode((current2.val + carry) % 10)
                    carry = 1 if (current2.val + carry) >= 10 else 0
                    sum_current.next = tmp
                    sum_current = sum_current.next

                    current2 = current2.next

        if carry == 1:
            sum_current.next = ListNode(carry)

        return sum_head