from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        # linked list length is 1
        if head is None or head.next is None:
            return head

        length = 0
        node = head
        while node:
            length += 1
            node = node.next

        k = k % length

        # from here, linked list length is 2 at least
        for _ in range(k):
            tail = self.get_tail(head)
            tail.next = head
            head = tail

        return head

    def get_tail(self, node: Optional[ListNode]):
        prev = node
        node = node.next

        while node.next:
            prev = node
            node = node.next

        prev.next = None
        return node
