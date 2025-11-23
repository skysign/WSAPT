import heapq
from collections import defaultdict
from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None

        mynode = head
        dt = defaultdict(list)
        heap = []

        while mynode:
            heapq.heappush(heap, mynode.val)
            val = mynode.val
            dt[val].append(mynode)
            mynode = mynode.next
            dt[val][-1].next = None

        head_val = heapq.heappop(heap)
        myhead = dt[head_val].pop(0)
        mynode = myhead

        while heap:
            val = heapq.heappop(heap)
            while dt[val]:
                mynode.next = dt[val].pop(0)
                mynode = mynode.next

        return myhead
