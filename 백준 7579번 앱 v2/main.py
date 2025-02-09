import sys


def solve():
    input = sys.stdin.readline
    N, M = map(int, input().strip().split())
    mems = [0] + list(map(int, input().strip().split()))
    costs = [0] + list(map(int, input().strip().split()))
    costs_sum = sum(costs)
    dp = [[0 for _ in range(costs_sum + 1)] for _ in range(N + 1)]
    answer = 100 * 100

    for idx_app in range(1, N + 1):
        for cost_crnt in range(0, costs_sum + 1):
            if cost_crnt < costs[idx_app]:
                dp[idx_app][cost_crnt] = dp[idx_app - 1][cost_crnt]
            else:
                dp[idx_app][cost_crnt] = max(dp[idx_app - 1][cost_crnt], dp[idx_app -1][cost_crnt - costs[idx_app]] + mems[idx_app])

            if dp[idx_app][cost_crnt] >= M:
                answer = min(answer, cost_crnt)

    print(answer)


if __name__ == '__main__':
    solve()
