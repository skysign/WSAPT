from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        reverse_head, current = None, head

        while current:
            tmp = current.next
            current.next = reverse_head
            reverse_head = current
            current = tmp

        return reverse_head
