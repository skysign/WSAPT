# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 1,2,3 떨어트리기 Python
#
# 유튜브 문제 풀이: https://youtu.be/2qgW5HFwt_g
#
# 파이썬 소스: https://bit.ly/3NcyNaY
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/150364

leaves = []


class Node:
    def __init__(self, number, target):
        self.number = number
        self.target = target
        self.children = []
        self.idxChildren = 0
        self.indexes = []
        self.idxChild = 0

    def add_child_node(self, child):
        self.children.append(child)
        self.children.sort()

    def send_index_to_leaf_node(self, idx):
        if len(self.children) > 0:
            rtn = self.children[self.idxChild].send_index_to_leaf_node(idx)
            self.idxChild += 1
            if self.idxChild == len(self.children):
                self.idxChild = 0

            return rtn

        if self.can_get_more():
            self.indexes.append(idx)
            return True

        return False

    def can_get_more(self):
        l = len(self.indexes)
        return l < self.target

    def __lt__(self, other):
        return self.number < other.number

    def get_answer(self):
        sum_number123, count_number123 = self.target, len(self.indexes)
        if can_make_123(sum_number123, count_number123) is False:
            return [-1]

        answer_of_leaf_node = []
        number123 = 3

        while sum_number123 > 0:
            if can_make_123(sum_number123 - number123, count_number123 - 1):
                answer_of_leaf_node.append(number123)
                sum_number123 -= number123
                count_number123 -= 1
            else:
                number123 -= 1

                if number123 < 0:
                    return [-1]

        answer_of_leaf_node.sort()

        return [self.indexes, answer_of_leaf_node]


def solution(edges, targets):
    global leaves

    nodes = [None for _ in range(101)]
    nodes[1] = Node(1, targets[0])

    for edge in edges:
        parent_number, child_number = edge
        if nodes[parent_number] is None:
            nodes[parent_number] = Node(parent_number, targets[parent_number - 1])
        if nodes[child_number] is None:
            nodes[child_number] = Node(child_number, targets[child_number - 1])

        nodes[parent_number].add_child_node(nodes[child_number])

    for idx in range(101):
        if nodes[idx] is not None and len(nodes[idx].children) == 0:
            leaves.append(nodes[idx])

    current_index = 0

    while True:
        if nodes[1].send_index_to_leaf_node(current_index) is False:
            return [-1]

        current_index += 1

        if all_leaf_nodes_can_make_123():
            break

    answer_length = 0
    tmps = []

    for leaf_node in leaves:
        indexes, numbers123 = leaf_node.get_answer()
        if indexes == [-1]:
            return [-1]

        answer_length += len(numbers123)
        tmps.append([indexes, numbers123])

    answer = [0 for _ in range(answer_length)]

    for tmp in tmps:
        indexes, numbers123 = tmp
        for idx in range(len(indexes)):
            answer[indexes[idx]] = numbers123[idx]

    return answer


def all_leaf_nodes_can_make_123():
    global leaves

    for leaf in leaves:
        if can_make_123(leaf.target, len(leaf.indexes)) is False:
            return False

    return True


def can_make_123(target, l):
    return l <= target <= (l * 3)
