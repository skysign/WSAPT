from typing import Optional


# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random


class Solution:
    def copyRandomList(self, head: Optional[Node]) -> Optional[Node]:
        if head == None:
            return None

        head_ori = Node(10 ** 4 * -1 - 1, head)
        head_copy = Node(10 ** 4 * -1 - 1)
        dt = {}

        node_ori = head_ori.next
        node_copy = head_copy
        while node_ori:
            node = Node(node_ori.val)
            dt[node_ori] = node
            node_ori = node_ori.next
            node_copy.next = node
            node_copy = node_copy.next

        node_ori = head_ori.next
        node_copy = head_copy.next
        while node_ori:
            if node_ori.random:
                node_copy.random = dt[node_ori.random]

            node_ori = node_ori.next
            node_copy = node_copy.next

        return head_copy.next