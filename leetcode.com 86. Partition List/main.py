from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def partition(self, head: Optional[ListNode], x: int) -> Optional[ListNode]:
        lessthan_x = ListNode(-201, None)
        greaterthanorequal_x = ListNode(201, None)
        lx, gx = lessthan_x, greaterthanorequal_x

        node = head

        while node:
            tmp = node
            node = node.next

            if tmp.val < x:
                lx.next = tmp
                lx = tmp
                lx.next = None
            else:
                gx.next = tmp
                gx = tmp
                gx.next = None

        lx.next = greaterthanorequal_x.next

        return lessthan_x.next
