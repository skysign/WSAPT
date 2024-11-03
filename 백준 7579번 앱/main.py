import sys


def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    memeories = [0] + list(map(int, sys.stdin.readline().strip().split()))
    costs = [0] + list(map(int, sys.stdin.readline().strip().split()))
    cost_max = sum(costs)
    dp = [[0 for _ in range(cost_max + 1)] for _ in range(N + 1)]
    answer = cost_max + 1

    for idx_app in range(1, N + 1):
        for cost_crnt in range(cost_max + 1):
            if cost_crnt < costs[idx_app]:
                dp[idx_app][cost_crnt] = dp[idx_app - 1][cost_crnt]
            else:
                dp[idx_app][cost_crnt] = max(dp[idx_app - 1][cost_crnt - costs[idx_app]] + memeories[idx_app], dp[idx_app - 1][cost_crnt])

            if dp[idx_app][cost_crnt] >= M:
                answer = min(answer, cost_crnt)

    print(answer)


if __name__ == '__main__':
    solve()
