from collections import deque

# 2022 KAKAO BLIND RECRUITMENT 파괴되지 않은 건물 python
#
# 유튜브 문제 풀이: https://youtu.be/Gu6uXd1bPMU
#
# 파이썬소스: http://bit.ly/3Gikm2M

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
