from typing import Optional, List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def averageOfLevels(self, root: Optional[TreeNode]) -> List[float]:
        queue = [TreeNode(-1, root, None)]
        answer = []
        queue2 = []

        while queue:
            node = queue.pop(0)
            if node.left:
                queue2.append(node.left)
            if node.right:
                queue2.append(node.right)

            if len(queue) == 0:
                mysum = 0
                count = len(queue2)
                if count == 0:
                    break

                for node in queue2:
                    mysum += node.val

                answer.append(mysum / count)
                queue = queue2
                queue2 = []

        return answer