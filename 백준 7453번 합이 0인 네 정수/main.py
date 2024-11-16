import sys

# pypy3를 선택해야 함, python3로 하면 시간 초과 발생
def solve():
    input = sys.stdin.readline
    n = int(input().strip())
    dts = [list(map(int, input().split())) for _ in range(n)]
    ABs, CDs = [], []

    for i in range(n):
        for j in range(n):
            ab = dts[i][0] + dts[j][1]
            cd = dts[i][2] + dts[j][3]
            ABs.append(ab)
            CDs.append(cd)

    ABs.sort()
    CDs.sort()

    answer = 0

    idx_ab = 0
    idx_cd = len(CDs) - 1

    while idx_ab < len(ABs) and 0 <= idx_cd:
        v = ABs[idx_ab] + CDs[idx_cd]

        if v == 0:
            idx_ab2 = idx_ab + 1
            # 같은 값이 반복되는 경우 갯수 새기
            while idx_ab2 < len(ABs) and ABs[idx_ab] == ABs[idx_ab2]:
                idx_ab2 += 1
            ab_len = idx_ab2 - idx_ab

            idx_cd2 = idx_cd - 1
            while 0 <= idx_cd2 and CDs[idx_cd] == CDs[idx_cd2]:
                idx_cd2 -= 1
            cd_len = idx_cd - idx_cd2

            answer += (ab_len * cd_len)

            idx_ab = idx_ab2
            idx_cd = idx_cd2
        elif v < 0:
            idx_ab += 1
        else:  # v > 0:
            idx_cd -= 1

    print(answer)


if __name__ == '__main__':
    solve()
