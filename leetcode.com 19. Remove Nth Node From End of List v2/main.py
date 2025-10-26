from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        if head.next == None:
            return None

        myhead = ListNode(-1, head)
        crnt, crnt2 = myhead, myhead

        for _ in range(n + 1):
            crnt2 = crnt2.next

        while crnt2 != None:
            crnt2 = crnt2.next
            crnt = crnt.next

        if crnt.next.next == None:
            crnt.next = None
        else:
            crnt.next = crnt.next.next

        return myhead.next
