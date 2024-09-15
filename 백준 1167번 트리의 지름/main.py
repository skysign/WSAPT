import sys

sys.setrecursionlimit(100000 + 10)
from collections import defaultdict


def solve():
    V = int(sys.stdin.readline().strip())
    edges = defaultdict(list)

    for _ in range(V):
        strs = list(map(int, sys.stdin.readline().strip().split()))
        # 마지막에 -1 제거
        strs = strs[:-1]
        # 제일 앞에 있는 vertext번호를 fr에 저장
        fr = list(map(int, strs[:1]))[0]
        strs = strs[1:]

        while strs:
            to = int(strs.pop(0))
            dist = int(strs.pop(0))
            edges[fr].append([to, dist])

    answer = [0]

    def dfs(pp, parent):
        if len(edges[parent]) == 0:
            return 0

        local_answer = []
        for to, dist in edges[parent]:
            if pp != to:
                tmp = dist + dfs(parent, to)
                local_answer.append(tmp)

        local_answer.sort(reverse=True)

        if len(local_answer) >= 2:
            l1, l2 = local_answer[:2]
            answer[0] = max(answer[0], l1 + l2)

        if len(local_answer) > 0:
            return local_answer[0]

        return 0

    rlt = dfs(-1, 1)

    print(max(answer[0], rlt))


if __name__ == '__main__':
    solve()
