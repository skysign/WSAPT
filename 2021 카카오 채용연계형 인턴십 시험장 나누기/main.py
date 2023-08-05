import sys
sys.setrecursionlimit(10**6)

class Node:
    def __init__(self, idx, v):
        self.idx = idx
        self.group = -1
        self.v = v
        self.parent = None;
        self.child_left = None;
        self.child_right = None;


nodes = []
nums = []
cnt_group = 0


def find_root():
    global nodes
    for node in nodes:
        if node.parent is None:
            return node.idx


def solution(k, num, links):
    global nodes, nums, cnt_group
    nums = num
    nodes = [Node(idx, nums[idx]) for idx in range(len(nums))]

    for idx in range(len(links)):
        left, right = links[idx]
        nodes[idx].child_left = left
        nodes[idx].child_right = right

        if left != -1:
            nodes[left].parent = idx
        if right != -1:
            nodes[right].parent = idx


    root_node = nodes[find_root()]

    if k == 1:
        return sum(nums)

    limit_low, limit_high = max(nums), sum(nums)+1

    while limit_low <= limit_high:

        limit_mid = int((limit_low + limit_high) /2)
        if get_group_count(limit_mid, root_node) <= k:
            limit_high = limit_mid -1
        else:
            limit_low = limit_mid +1

    return limit_high +1


def get_group_count(limit, root_node):
    global cnt_group
    cnt_group = 0
    dfs(limit, root_node.idx)
    return cnt_group +1


def dfs(limit, idx_node):
    global nodes, nums, cnt_group

    left_v, right_v = 0, 0
    node = nodes[idx_node]

    if node.child_left != -1:
        left_v = dfs(limit, node.child_left)

    if node.child_right != -1:
        right_v = dfs(limit, node.child_right)

    if nums[node.idx] + left_v + right_v <= limit:
        return nums[node.idx] + left_v + right_v

    if nums[node.idx] + min(left_v, right_v) <= limit:
        cnt_group += 1
        return nums[node.idx] + min(left_v, right_v)

    cnt_group +=2

    return nums[node.idx]