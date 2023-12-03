from collections import defaultdict

def solution(dirs):
    crntyx = [0, 0]

    # U: 위쪽으로 한 칸 가기
    # D: 아래쪽으로 한 칸 가기
    # R: 오른쪽으로 한 칸 가기
    # L: 왼쪽으로 한 칸 가기
    move = {
        'U': [1, 0],
        'D': [-1, 0],
        'R': [0, 1],
        'L': [0, -1]
    }

    visited = defaultdict(tuple)
    answer = 0

    for dir in dirs:
        dyx = move[dir]
        newy = crntyx[0] + dyx[0]
        newx = crntyx[1] + dyx[1]

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