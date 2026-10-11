import sys


def solution(info, n, m):
    answer_a, answer_b = sys.maxsize, sys.maxsize
    dp = set()

    def dfs(idx, a, b, dp):
        nonlocal answer_a, answer_b, n, m, info

        if idx == len(info):
            if a < answer_a:
                answer_a = a
                answer_b = b
            return

        visited = '{idx}-{a}-{b}'.format(idx=idx, a=a, b=b)
        if dp.__contains__(visited):
            return
        dp.add(visited)

        if a + info[idx][0] < n and a + info[idx][0] < answer_a:
            dfs(idx + 1, a + info[idx][0], b, dp)

        if b + info[idx][1] < m:
            dfs(idx + 1, a, b + info[idx][1], dp)

    dfs(0, 0, 0, dp)

    if answer_a > n or answer_b > m:
        return -1

    return answer_a
