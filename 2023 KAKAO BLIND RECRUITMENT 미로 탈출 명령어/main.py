import sys

sys.setrecursionlimit(10 ** 9)


def solution(n, m, y, x, r, c, k):
    y, x, r, c = y - 1, x - 1, r - 1, c - 1,
    min_distance = abs(y - r) + abs(x - c)

    if  min_distance > k:
        return 'impossible'

    if min_distance % 2 != k % 2:
        return 'impossible'

    answer = dfs(n, m, y, x, r, c, k, [])

    if None == answer:
        return 'impossible'

    return ''.join(answer)


def dfs(n, m, y, x, dst_y, dst_x, depth, pathes):
    if depth == 0:
        if y == dst_y and x == dst_x:
            return pathes
        else:
            return None

    # d: 아래쪽으로, l: 왼쪽으로, r: 오른쪽으로, u: 위쪽으로
    for dy, dx, dir in [[1, 0, 'd'], [0, -1, 'l'], [0, 1, 'r'], [-1, 0, 'u']]:
        ny, nx = y + dy, x + dx
        if 0 <= ny < n and 0 <= nx < m:
            distance = abs(ny - dst_y) + abs(nx - dst_x)
            if distance > depth -1:
                continue

            rtn = dfs(n, m, ny, nx, dst_y, dst_x, depth - 1, pathes + [dir])
            if rtn != None:
                return rtn

    return None
