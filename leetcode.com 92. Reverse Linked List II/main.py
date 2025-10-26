from typing import Optional, List


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def reverseBetween(self, head: Optional[ListNode], left: int, right: int) -> Optional[ListNode]:
        myhead = ListNode(-501, head)
        length = right - left + 1
        node = myhead

        stk: List[ListNode] = []

        for _ in range(left - 1):
            node = node.next

        nodel = node.next

        for _ in range(length):
            stk.append(nodel)
            nodel = nodel.next

        node2 = nodel


        while stk:
            node.next = stk.pop(-1)
            node = node.next

        node.next = node2

        return myhead.next