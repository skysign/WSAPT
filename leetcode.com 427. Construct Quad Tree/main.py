from typing import List


# Definition for a QuadTree node.
class Node:
    def __init__(self, val, isLeaf, topLeft, topRight, bottomLeft, bottomRight):
        self.val = val
        self.isLeaf = isLeaf
        self.topLeft = topLeft
        self.topRight = topRight
        self.bottomLeft = bottomLeft
        self.bottomRight = bottomRight


class Solution:
    def construct(self, grid: List[List[int]]) -> 'Node':
        if len(grid) == 1:
            return Node(True if grid[0][0] == 1 else False, True, None, None, None, None)

        def my_construct(grid, length, sr, sc, delta):
            one, zero = False, False

            for row in grid[sr:sr + delta]:
                col = row[sc: sc + delta]
                for local_v in col:
                    if local_v == 0:
                        zero |= True
                    if local_v == 1:
                        one |= True

            if one == False and zero == True:
                return Node(False, True, None, None, None, None)

            if one == True and zero == False:
                return Node(True, True, None, None, None, None)

            return Node(True, False,
                        my_construct(grid, length, sr, sc, delta // 2),
                        my_construct(grid, length, sr, sc + delta // 2, delta // 2),
                        my_construct(grid, length, sr + delta // 2, sc, delta // 2),
                        my_construct(grid, length, sr + delta // 2, sc + delta // 2, delta // 2),
                        )

        return my_construct(grid, len(grid), 0, 0, len(grid))
