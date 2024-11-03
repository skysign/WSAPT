import sys


def solve():
    N = int(sys.stdin.readline().strip())
    dts = list(map(int, sys.stdin.readline().strip().split()))
    dts.sort()

    MAX = 1000000000 + 1

    answer = [MAX, MAX, MAX]

    # left를 반드시 포함하는 3개의 용액을 찾는다.
    for left in range(0, N - 2):
        mid = left + 1
        right = N - 1

        while mid < right:
            if abs(dts[left] + dts[mid] + dts[right]) < abs(sum(answer)):
                answer[0], answer[1], answer[2] = dts[left], dts[mid], dts[right]

            if dts[left] + dts[mid] + dts[right] > 0:
                right -= 1
            elif dts[left] + dts[mid] + dts[right] < 0:
                mid += 1
            else:  # 0
                print(' '.join(map(str, answer)))
                return

    print(' '.join(map(str, answer)))


if __name__ == '__main__':
    solve()
