import sys


def solve():
    n = int(sys.stdin.readline().strip())
    m = int(sys.stdin.readline().strip())

    maps = [[sys.maxsize for _ in range(n + 1)] for _ in range(n + 1)]
    for _ in range(m):
        fr, to, cost = map(int, sys.stdin.readline().strip().split(' '))
        maps[fr][to] = min(maps[fr][to], cost)

    for mid in range(1, n + 1):
        for fr in range(1, n + 1):
            for to in range(1, n + 1):
                if maps[fr][mid] != sys.maxsize and maps[mid][to] != sys.maxsize:
                    if maps[fr][to] > maps[fr][mid] + maps[mid][to]:
                        maps[fr][to] = maps[fr][mid] + maps[mid][to]

    for row in range(1, n + 1):
        for col in range(1, n + 1):
            if maps[row][col] == sys.maxsize or row == col:
                maps[row][col] = 0

    for idx in range(1, n + 1):
        cols = maps[idx][1:]
        cols = list(map(str, cols))
        print(' '.join(cols))


if __name__ == '__main__':
    solve()
