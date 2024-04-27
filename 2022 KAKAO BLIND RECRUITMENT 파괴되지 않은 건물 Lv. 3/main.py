from typing import List


def solution(board: List[List[int]], skill: List[List[int]]):
    sums = [[0 for _ in range(len(board[0]) + 1)] for _ in range(len(board)+1)]

    for type, r1, c1, r2, c2, degree in skill:
        r2, c2 = r2 + 1, c2 +1
        if type == 1:
            sums[r1][c1] -= degree
            sums[r1][c2] += degree
            sums[r2][c1] += degree
            sums[r2][c2] -= degree
        else:
            sums[r1][c1] += degree
            sums[r1][c2] -= degree
            sums[r2][c1] -= degree
            sums[r2][c2] += degree

    for r in range(len(sums)):
        for c in range(len(sums[0])-1):
            sums[r][c+1] += sums[r][c]

    for c in range(len(sums[0])):
        for r in range(len(sums)-1):
            sums[r+1][c] += sums[r][c]

    answer = 0
    for r in range(len(board)):
        for c in range(len(board[0])):
            if board[r][c] + sums[r][c] >= 1:
                answer += 1
    return answer
