from typing import List


def solution(numbers: List[int]):
    answer = []

    for n in numbers:
        answer.append(check_binarytree(n))

    return answer


def check_binarytree(n: int) -> int:
    b = bin(n)[2:]
    if len(b) == 1:
        if b == '1':
            return 1
        else:
            return 0

    l, length = 2, 0

    while l <= len(b):
        l *= 2
        length = l - 1

    begin0 = ['0'] * (length - len(b))
    b = ''.join(begin0) + b

    return check(b)


def check(b: str) -> int:
    if len(b) == 1:
        return 1

    half = len(b) // 2
    parent = b[half:half + 1]
    left = b[:half]
    right = b[half + 1:]

    if parent == '0':
        if left.find('1') != -1:
            return 0
        if right.find('1') != -1:
            return 0

    return check(left) and check(right)
