import sys
from collections import deque


def solve():
    input = sys.stdin.readline
    N, M = map(int, input().strip().split())
    junan_y, junan_x, thief_y, thief_x = map(int, input().strip().split())
    junan_y, junan_x, thief_y, thief_x = junan_y - 1, junan_x - 1, thief_y - 1, thief_x - 1
    maps = [list(input().strip()) for _ in range(N)]
    answer = 1
    students = deque()

    while True:
        visited = [[False for _ in range(M)] for _ in range(N)]

        queue = deque([[junan_y, junan_x]])
        while queue:
            sy, sx = queue.popleft()
            if visited[sy][sx]:
                continue

            visited[sy][sx] = True

            dyxs = [[-1, 0], [0, 1], [1, 0], [0, -1]]
            for dy, dx in dyxs:
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


if __name__ == '__main__':
    solve()
