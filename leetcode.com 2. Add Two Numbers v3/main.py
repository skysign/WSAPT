from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        carry = 0
        head_rlt = ListNode(0)
        rlt = head_rlt
        ll, rr = l1, l2

        while ll or rr or carry:
            value = carry

            if ll:
                value += ll.val
                ll = ll.next

            if rr:
                value += rr.val
                rr = rr.next

            carry, value = divmod(value, 10)
            rlt.next = ListNode(value)
            rlt = rlt.next

        return head_rlt.next