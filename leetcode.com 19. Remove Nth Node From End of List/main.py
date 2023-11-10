# Definition for singly-linked list.
from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        if head.next == None:
            return None
        elif head.next.next == None:
            if n == 1:
                head.next = None
                return head
            elif n == 2:
                # head = head.next
                return head.next

        left = ListNode(-1, head)
        right = head

        for _ in range(n):
            right = right.next

        while right:
            left = left.next
            right = right.next

        # head가 변경되는 경우
        if left.next == head:
            left.next = left.next.next
            head = left.next
        else:
            left.next = left.next.next

        return head


    # removeNthFromEnd2가 removeNthFromEnd() 보다 더 빠름
    def removeNthFromEnd2(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        stk = []
        stk2 = []

        node = head

        while node:
            stk.append(node)
            node = node.next

        for cnt in range(1, len(stk) +1, 1):
            node = stk.pop()

            if cnt != n:
                stk2.append(node)

        myhead = ListNode(-1)

        for idx in range(len(stk2)):
            node = stk2.pop(0)

            if idx == 0:
                node.next = None
                myhead.next = node
            else:
                nextnext = myhead.next
                myhead.next = node
                node.next = nextnext

        return myhead.next


#
#
#     def genLL(self, nums):
#         head = ListNode()
#         tail = head
#
#         for num in nums:
#             tail.next = ListNode(num)
#             tail = tail.next
#
#         return head.next
#
#
# if __name__:
#     sol = Solution()
#     # head = sol.genLL([1, 2, 3, 4])
#     # head = sol.genLL([1, 2, 3, 4, 5])
#     head = sol.genLL([1, 2, 3])
#     sol.removeNthFromEnd(head, 1)
#
#     while head:
#         print(head.val)
#         head = head.next