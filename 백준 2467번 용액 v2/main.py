import sys


def solve():
    input = sys.stdin.readline
    N = int(input().strip())
    dts = list(map(int, input().strip().split()))

    idx_l, idx_r = 0, N - 1
    answer, answer_l, answer_r = sys.maxsize, 0, 0

    while idx_l < idx_r:
        answer_local = dts[idx_l] + dts[idx_r]

        if abs(answer) > abs(answer_local):
            answer, answer_l, answer_r = answer_local, dts[idx_l], dts[idx_r]

        if answer_local < 0:
            idx_l += 1
        elif answer_local > 0:
            idx_r -= 1
        else:
            break

    print("{0} {1}".format(answer_l, answer_r))


if __name__ == '__main__':
    solve()
