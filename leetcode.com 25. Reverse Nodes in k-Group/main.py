from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        crnt = head
        fast = self.move_n(crnt, k -1)

        while crnt and fast:
            # tmp = fast.val
            # fast.val = crnt.val
            # crnt.val = tmp
            self.reverse(crnt, k)

            crnt = self.move_n(crnt, k)
            fast = self.move_n(fast, k)

        return head

    def reverse(self, head: Optional[ListNode], k):
        stk = []

        crnt = head
        n = k
        while crnt and n:
            stk.append(crnt.val)
            n -= 1
            crnt = crnt.next

        crnt = head
        n = k
        while crnt and n:
            crnt.val = stk.pop()
            n -= 1
            crnt = crnt.next

    def move_n(self, head: Optional[ListNode], n):
        cnt = 0
        crnt = head

        while crnt and cnt < n:
            crnt = crnt.next
            cnt += 1

            if crnt == None:
                return None

        return crnt

    def genLL(self, nums):
        head = ListNode()
        tail = head

        for num in nums:
            tail.next = ListNode(num)
            tail = tail.next

        return head.next


if __name__:
    sol = Solution()
    # head = sol.genLL([1, 2, 3, 4])
    # head = sol.genLL([1, 2, 3, 4, 5])
    head = sol.genLL([1,2,3,4,5])
    head = sol.reverseKGroup(head, 3)

    while head:
        print(head.val)
        head = head.next