import sys

# 10 20 30 11 12 13 14
# LIS 값이 변경되는 순서
# 10
# 10 20 30
# 10 11 30 <- binary search로 11이 20의 위치를 차지함
# 10 11 12 <- binary search로 12가 30의 위치를 차지함
# 10 11 12 13
# 10 11 12 13 14
def solve():
    input = sys.stdin.readline
    N = int(input().strip())
    dts = list(map(int, input().strip().split()))
    LIS = [0]

    for i in range(N):
        if LIS[-1] < dts[i]:
            LIS.append(dts[i])
        else:
            idx = binary_search(LIS, dts[i])
            LIS[idx] = dts[i]

    print(len(LIS) - 1)


# binary_search함수 검증
# dts가 길이 1 일 때 X, dts 10, v 9 <- 이 경우가 존재 하지 않음
# 길이 2일 대, dts 10, 20 , rnt 0 v 9, rnt 0 v 10, rtn 1 v 11
# 길이 3일 때, dts 10, 20, 30, rtn 0 v 9, 10
# 길이 3일 때, dts 10, 20, 30, rtn 1 v 19
# 길이 3일 때, dts 10, 20, 30, rtn 2 v 21
def binary_search(dts, v):
    bgn, end = 0, len(dts) - 1

    while bgn <= end:
        mid = (bgn + end) // 2

        if dts[mid] == v:
            return mid
        elif dts[mid] < v:
            bgn = mid + 1
        else: # v < dts[mid]:
            end = mid - 1

    return bgn


if __name__ == '__main__':
    solve()
