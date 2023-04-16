# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 택배 배달과 수거하기 Python
#
# 유튜브 문제 풀이: https://youtu.be/XExcM0Gqbd4
#
# 파이썬 소스: https://bit.ly/3KNLBUe
#
# 문제 링크: https://bit.ly/41yCaOT

def solution(numbers):
    answer = []

    for number in numbers:
        binary_string: str = binary_scale(number)
        binary_length = len(binary_string)
        full_length, tree_level = get_full_binarytree_length(binary_length)
        binary_string = '0' * (full_length - binary_length) + binary_string
        mid = int(full_length / 2)
        tf = visit_tree_in_order(tree_level, mid, binary_string)
        answer.append(tf)

    return answer


def visit_tree_in_order(level, mid, data):
    if level == 1:
        return 1

    parent = data[mid]
    idx_left = mid - int(2 ** (level - 1) / 2)
    left_child = data[idx_left]
    idx_right = mid + int(2 ** (level - 1) / 2)
    right_child = data[idx_right]

    if parent == '0':
        if left_child == '1' or right_child == '1':
            return 0

    # parent == 1
    if visit_tree_in_order(level - 1, idx_left, data) == 0:
        return 0

    if visit_tree_in_order(level - 1, idx_right, data) == 0:
        return 0

    return 1


def get_full_binarytree_length(binary_number_length):
    binary_tree_length = 1
    tree_level = 1

    while binary_number_length > binary_tree_length:
        tree_level += 1
        binary_tree_length += 2 ** (tree_level-1)

    return binary_tree_length, tree_level


def binary_scale(dividend):
    divisor = 2
    remainder = dividend % divisor
    quotient = int(dividend / divisor)

    if quotient == 0:
        return str(remainder)

    return binary_scale(quotient) + str(remainder)
