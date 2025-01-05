import sys


def solve():
    input = sys.stdin.readline
    N, S = list(map(int, input().strip().split()))
    dts = list(map(int, input().strip().split()))

    idx_left, idx_right = 0, 0
    sum_local = dts[0]
    answer = sys.maxsize

    while idx_left <= idx_right and idx_right < N:
        if sum_local >= S:
            answer = min(answer, idx_right - idx_left + 1)
            sum_local -= dts[idx_left]
            idx_left += 1
        else:
            idx_right += 1
            if idx_right < N:
                sum_local += dts[idx_right]
            else:
                break


    if answer == sys.maxsize:
        print(0)
    else:
        print(answer)


if __name__ == '__main__':
    solve()
