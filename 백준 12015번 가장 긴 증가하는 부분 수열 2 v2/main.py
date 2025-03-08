import sys


def solve():
    input = sys.stdin.readline
    N = int(input().strip())
    dts = list(map(int, input().strip().split()))
    LIS = [0]

    for dt in dts:
        if LIS[-1] < dt:
            LIS.append(dt)
        else:
            idx = binary_search(LIS, dt)
            LIS[idx] = dt

    print(len(LIS) - 1)


def binary_search(LIS, dt):
    bgn, end = 0, len(LIS) - 1

    while bgn <= end:
        mid = (bgn + end) // 2
        if dt == LIS[mid]:
            return mid
        elif dt < LIS[mid]:
            end = mid - 1
        else:  # dt > LIS[mid]
            bgn = mid + 1

    return bgn


if __name__ == '__main__':
    solve()
