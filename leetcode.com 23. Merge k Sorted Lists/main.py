from typing import List, Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        arr = []

        for crnt in lists:
            while crnt:
                arr.append(crnt.val)
                crnt = crnt.next

        head = crnt = ListNode(-1)

        for v in sorted(arr):
            crnt.next = ListNode(v)
            crnt = crnt.next

        return head.next