import string
from typing import Optional


# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Codec:
    def serialize(self, root):
        if not root:
            return ''

        input_queue: list[TreeNode] = []
        answer: list[string] = []
        input_queue.append(root)

        while len(input_queue) > 0:
            node: Optional[TreeNode] = input_queue.pop(0)

            if node:
                input_queue.append(node.left)
                input_queue.append(node.right)

            if not node:
                answer.append('null,')
            else:
                answer.append(str(node.val) + ',')

        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """

        return (''.join(answer))[:-1]

    def deserialize(self, data):
        if data == '':
            return None

        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """
        input_queue: list[string] = data.split(',')
        val = input_queue.pop(0)
        node = TreeNode(val)
        root = node
        input_node: list[TreeNode] = [node]

        while len(input_queue) > 0:
            node = input_node.pop(0)

            val = input_queue.pop(0)
            if val == 'null':
                node.left = None
            else:
                node.left = TreeNode(int(val))
                input_node.append(node.left)

            val = input_queue.pop(0)
            if val == 'null':
                node.right = None
            else:
                node.right = TreeNode(int(val))
                input_node.append(node.right)

        return root

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))
