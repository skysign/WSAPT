from collections import defaultdict

# programmers.co.kr 코딩테스트 연습 Summer/Winter Coding(~2018) 방문 길이
#
# 유튜브 문제 풀이:
#
# 파이썬 소스: https://bit.ly/3N84H9e
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/49994

def solution(dirs):
    answer = 0
    # U: 위쪽으로 한 칸 가기
    # D: 아래쪽으로 한 칸 가기
    # R: 오른쪽으로 한 칸 가기
    # L: 왼쪽으로 한 칸 가기
    moves = {
        'U': [1, 0],
        'D': [-1, 0],
        'R': [0, 1],
        'L': [0, -1]
    }

    visited = defaultdict(tuple)
    crntyx = [0, 0]

    for dir in dirs:
        dy, dx = moves[dir]
        newy = crntyx[0] + dy
        newx = crntyx[1] + dx

        if not (-5 <= newy <= 5 and -5 <= newx <= 5):
            continue

        path1 = (crntyx[0], crntyx[1], newy, newx)
        path2 = (newy, newx, crntyx[0], crntyx[1])

        if path1 not in visited.keys() and path2 not in visited.keys():
            answer += 1

        visited[path1] = 1
        visited[path2] = 1

        crntyx[0], crntyx[1] = newy, newx

    return answer