from collections import deque

# 2022 카카오 신입 공채 1차 온라인 코딩테스트 사라지는 발판 Python
#
# 유튜브 문제 풀이: https://youtu.be/Zxv3ZAqSRBk
#
# 파이썬소스:
#
# 문제: https://school.programmers.co.kr/learn/courses/30/lessons/92345

def solution(board, skill):
    answer = 0
    skills = deque(skill)
    prefix = [[0 for c in range(len(board[0]) + 1)] for r in range(len(board) + 1)]

    while len(skills) > 0:
        type, r1, c1, r2, c2, degree = skills.popleft()
        r2 += 1
        c2 += 1

        if type == 1:
            degree *= -1

        prefix[r1][c1] += degree
        prefix[r1][c2] += (degree * -1)
        prefix[r2][c1] += (degree * -1)
        prefix[r2][c2] += degree

    # 위 -> 아래
    for r in range(1, len(prefix)):
        for c in range(len(prefix[0])):
            prefix[r][c] += prefix[r-1][c]

    # 왼 -> 오른쪽
    for r in range(len(prefix)):
        for c in range(1, len(prefix[0])):
            prefix[r][c] += prefix[r][c - 1]

    for r in range(0, len(board)):
        for c in range(0, len(board[0])):
            if (board[r][c] + prefix[r][c]) > 0:
                answer += 1

    return answer
