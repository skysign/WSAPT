import sys


def solve():
    N, S = map(int, sys.stdin.readline().strip().split())
    dts = list(map(int, sys.stdin.readline().strip().split()))

    idx_left, idx_right = 0, 0
    answer_length = sys.maxsize
    sum = dts[0]

    while idx_left <= idx_right:
        if sum >= S:
            answer_length = min(answer_length, idx_right - idx_left + 1)
            sum -= dts[idx_left]
            idx_left += 1
        else:
            idx_right += 1

            if idx_right < N:
                sum += dts[idx_right]
            else:
                break


    if answer_length == sys.maxsize:
        print(0)
    else:
        print(answer_length)


if __name__ == '__main__':
    solve()
