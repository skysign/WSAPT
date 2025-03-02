import sys
from collections import deque


def solve():
    input = sys.stdin.readline
    N, M = map(int, input().strip().split())
    junan_y, junan_x, thief_y, thief_x = map(int, input().strip().split())
    junan_y, junan_x, thief_y, thief_x = junan_y - 1, junan_x - 1, thief_y - 1, thief_x - 1
    maps = []


    for _ in range(N):
        maps.append(list(input().strip()))

    queue = deque([[junan_y, junan_x]])

    dys = [-1, 0, 1, 0]
    dxs = [0, 1, 0, -1]

    answer = 1
    students = deque()

    while True:
        visited = [[False for _ in range(M)] for _ in range(N)]

        while queue:
            sy, sx = queue.popleft()
            if visited[sy][sx]:
                continue

            visited[sy][sx] = True

            for i in range(len(dys)):
                dy, dx = dys[i], dxs[i]
                y, x = sy + dy, sx + dx

                if 0 <= y < N and 0 <= x < M and visited[y][x] == False:
                    if y == thief_y and x == thief_x:
                        print(answer)
                        return
                    elif maps[y][x] == '1':
                        students.append([y, x])
                    else:
                        queue.append([y, x])

        while students:
            y, x = students.popleft()
            maps[y][x] = 0

        answer += 1

        queue.append([junan_y, junan_x])


if __name__ == '__main__':
    solve()
