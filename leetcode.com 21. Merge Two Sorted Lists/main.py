from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        tail = ListNode(-1)
        head = tail

        while list1 and list2:
            if list1.val < list2.val:
                tail.next = list1
                list1 = list1.next
            else:
                tail.next = list2
                list2 = list2.next

            if tail.next:
                tail = tail.next
                tail.next = None

        if list1:   # list1에 남은 아이템 있음
            while list1:
                tail.next = list1
                list1 = list1.next
                tail = tail.next
                tail.next = None
        else:   # list2에 남은 아이템 있음
            while list2:
                tail.next = list2
                list2 = list2.next
                tail = tail.next
                tail.next = None

        return head.next
