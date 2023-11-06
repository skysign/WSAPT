from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def reorderList(self, head: Optional[ListNode]) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        if head.next == None:
            return

        stk = []
        myhead = head
        while myhead:
            stk.append(myhead)
            myhead = myhead.next

        length = len(stk)
        mylen = int(length/2)

        myhead = head
        for _ in range(mylen):
            myhead_next = myhead.next
            node = stk.pop()
            myhead.next = node
            node.next = myhead_next
            myhead = myhead_next
        else:
            myhead_next.next = None

        myhead = None

    def genLL(self, nums):
        head = ListNode()
        tail = head

        for num in nums:
            tail.next = ListNode(num)
            tail = tail.next

        return head.next


    def print_list(self, node):
        tmp = node
        while tmp:
            print(tmp.val)
            tmp = tmp.next

        print()

if __name__:
    sol = Solution()
    # head = sol.genLL([1, 2, 3, 4])
    # head = sol.genLL([1, 2, 3, 4, 5])
    head = sol.genLL([1])
    sol.reorderList(head)

    while head:
        print(head.val)
        head = head.next

