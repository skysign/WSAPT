import sys


def solve():
    input = sys.stdin.readline
    N = int(input().strip())
    dts = [list(map(int, input().strip().split())) for _ in range(N)]
    ABs, CDs = [], []

    for i in range(N):
        for j in range(N):
            ABs.append(dts[i][0] + dts[j][1])
            CDs.append(dts[i][2] + dts[j][3])

    ABs.sort()
    CDs.sort()

    idx_ab = 0
    idx_cd = (N * N) - 1
    answer = 0

    while idx_ab < (N * N) and 0 <= idx_cd:
        v = ABs[idx_ab] + CDs[idx_cd]

        if v == 0:
            idx_ab2 = idx_ab + 1
            while idx_ab2 < (N * N) and ABs[idx_ab] == ABs[idx_ab2]:
                idx_ab2 += 1
            len_ab = idx_ab2 - idx_ab

            idx_cd2 = idx_cd - 1
            while 0 <= idx_cd2 and CDs[idx_cd] == CDs[idx_cd2]:
                idx_cd2 -= 1
            len_cd = idx_cd - idx_cd2

            answer += (len_ab * len_cd)

            idx_ab = idx_ab2
            idx_cd = idx_cd2

        elif v < 0:
            idx_ab += 1
        else:  # V > 0
            idx_cd -= 1

    print(answer)


if __name__ == '__main__':
    solve()
