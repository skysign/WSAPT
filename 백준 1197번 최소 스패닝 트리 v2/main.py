import sys

sys.setrecursionlimit(10 ** 9)


def solve():
    input = sys.stdin.readline
    V, E = list(map(int, input().strip().split()))
    dt = [i for i in range(V + 1)]
    edges = []

    for _ in range(E):
        v1, v2, w = list(map(int, input().strip().split()))
        edges.append([w, v1, v2])

    edges.sort()
    answer = 0

    for w, v1, v2 in edges:
        v1_root = union_find(v1, dt)
        v2_root = union_find(v2, dt)

        if v1_root != v2_root:
            answer += w
            dt[v1_root] = v2_root

    print(answer)


def union_find(v, dt):
    if dt[v] != v:
        dt[v] = union_find(dt[v], dt)

    return dt[v]


if __name__ == '__main__':
    solve()
