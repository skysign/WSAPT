import sys
sys.setrecursionlimit(100000)


def solve():
    input = sys.stdin.readline
    T = int(input().strip())

    for _ in range(T):
        N, K = map(int, input().strip().split())
        dts = [0] + list(map(int, input().strip().split()))
        dp = [-1 for _ in range(N + 1)]
        incoming = [[] for _ in range(N + 1)]
        for _ in range(K):
            to, fr = map(int, input().strip().split())
            incoming[fr].append(to)

        target = int(input().strip())

        dfs(dts, dp, incoming, target)

        print(dp[target])


def dfs(dts, dp, incoming, node):
    answer_local = 0

    if len(incoming[node]) > 0:
        for fr in incoming[node]:
            if dp[fr] == -1:
                dfs(dts, dp, incoming, fr)

            answer_local = max(answer_local, dp[fr])

        dp[node] = answer_local + dts[node]
    else:
        dp[node] = dts[node]


if __name__ == '__main__':
    solve()
