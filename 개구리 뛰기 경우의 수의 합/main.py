import sys

# 개구리 뛰기, 문제 풀이
def solution_original(N, stones, K):
    dt = [0 for _ in range(1000001)]
    dp = [-1 for _ in range(1000001)]
    dt[0] = 1
    dp[0] = 0
    idx_dst = 0 # destination(최종 목적지), 즉 stones의 마지막 숫자

    for stone in stones.split(' '):
        idx_stone = int(stone)
        dt[idx_stone] = 1 # dt[x] 1이면 개구리가 밟을 수 있음
        idx_dst = idx_stone

    for idx in range(1, idx_dst + 1):
        for k in range(1, K+1):
            if idx - k >= 0 and dt[idx - k] == 1:
                if dp[idx] == -1:
                    dp[idx] = dp[idx - k] + 1
                elif dp[idx - k] + 1 < dp[idx]:
                    dp[idx] = dp[idx - k] + 1

    return dp[idx_dst]


# 개구리 뛰기 도착할 수 있는 모든 경우의 수의 합, 문제 풀이
def solution_sum(N, stones, K):
    dt = [0 for _ in range(1000001)]
    dp = [-1 for _ in range(1000001)]
    dt[0] = 1
    dp[0] = 1 # 1으로 변경됨
    idx_dst = 0 # destination(최종 목적지), 즉 stones의 마지막 숫자

    for stone in stones.split(' '):
        idx_stone = int(stone)
        dt[idx_stone] = 1 # dt[x] 1이면 개구리가 밟을 수 있음
        idx_dst = idx_stone

    for idx in range(1, idx_dst + 1):
        for k in range(1, K+1):
            if idx - k >= 0 and dt[idx - k] == 1:
                if dp[idx] == -1:
                    dp[idx] = dp[idx - k]
                else:
                    dp[idx] = dp[idx] + dp[idx - k]

    return dp[idx_dst]