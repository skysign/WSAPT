from typing import List, Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        head = ListNode()
        crnt = head

        for node in lists:
            while node:
                crnt.next = node
                node = node.next
                crnt = crnt.next

        if head.next is None or head.next.next is None:
            return head.next

        return self.divide(head.next)

    def divide(self, head: Optional[ListNode]):
        if head.next is None:
            return head

        first, mid, dummy = head, head, head
        prev_mid = mid

        while mid and dummy:
            prev_mid = mid
            mid = mid.next
            dummy = dummy.next
            if dummy is not None:
                dummy = dummy.next

        prev_mid.next = None
        first = self.divide(first)
        mid = self.divide(mid)

        return self.merge(first, mid)

    def merge(self, n1: Optional[ListNode], n2: Optional[ListNode]):
        rtn = ListNode()
        crnt = rtn

        while n1 and n2:
            if n1.val < n2.val:
                crnt.next = n1
                n1 = n1.next
            else:
                crnt.next = n2
                n2 = n2.next

            crnt = crnt.next

        if n1:
            crnt.next = n1

        if n2:
            crnt.next = n2

        return rtn.next