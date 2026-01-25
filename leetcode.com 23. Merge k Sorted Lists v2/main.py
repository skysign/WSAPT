import sys
from typing import List, Optional
import heapq

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        dummy_node = ListNode()
        head = dummy_node

        while lists.__contains__(None):
            lists.remove(None)

        while lists:
            min_val = sys.maxsize
            min_idx = 0

            for idx in range(len(lists)):
                if min_val > lists[idx].val:
                    min_val = lists[idx].val
                    min_idx = idx

            head.next = lists[min_idx]
            head = head.next

            if lists[min_idx].next is None:
                lists.pop(min_idx)
            else:
                lists[min_idx] = lists[min_idx].next

        return dummy_node.next