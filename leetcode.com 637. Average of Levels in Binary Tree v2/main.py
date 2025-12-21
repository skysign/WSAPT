from typing import Optional, List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def averageOfLevels(self, root: Optional[TreeNode]) -> List[float]:
        dict = {}
        node = root
        queue = [[node, 0]]

        while queue:
            node, level = queue.pop(0)
            if node is None:
                continue

            if dict.keys().__contains__(level):
                dict[level][0] += node.val
                dict[level][1] += 1
            else:
                dict[level] = [node.val, 1]

            queue.append([node.left, level + 1])
            queue.append([node.right, level + 1])

        return [dict[key][0] / dict[key][1] for key in sorted(dict.keys())]
