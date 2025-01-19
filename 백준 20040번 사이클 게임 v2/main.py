import sys


def solve():
    input = sys.stdin.readline
    N, M = map(int, input().strip().split())

    vertexes = [i for i in range(N)]

    for m in range(1, M + 1):
        e1, e2 = map(int, input().strip().split())
        e1, e2 = min(e1, e2), max(e1, e2)

        r1 = find(vertexes, e1)
        r2 = find(vertexes, e2)

        if r1 == r2:
            print(m)
            return
        else:
            union(vertexes, min(r1, r2), max(r1, r2))

    print(0)

def union(vertexes, r1, r2):
    vertexes[r2] = r1


def find(vertexes, node):
    parent = vertexes[node]

    if parent == node:
        return parent

    vertexes[parent] = find(vertexes, parent)

    return vertexes[parent]


if __name__ == '__main__':
    solve()
