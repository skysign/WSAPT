# Definition for a Node.
from typing import Optional


class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random

class Solution:
    def copyRandomList(self, head: Optional[Node]) -> Optional[Node]:
        cache = {None: None}

        mycurrent = head

        while mycurrent:
            cache[mycurrent] = Node(mycurrent.val)
            mycurrent = mycurrent.next

        mycurrent = head
        while mycurrent:
            cache[mycurrent].next = cache[mycurrent.next]
            cache[mycurrent].random = cache[mycurrent.random]

            mycurrent = mycurrent.next

        return cache[head]