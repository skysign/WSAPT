import sys


def solve():
    input = sys.stdin.readline
    N = int(input().strip())
    dts = list(map(int, input().strip().split()))
    dts.sort()

    MAX = 1000000000 + 1
    answers = [MAX, MAX, MAX]

    # mid를 반드시 포함하는 3개의 용액을 찾는다.
    for mid in range(1, N - 1):
        left, right = mid - 1, mid + 1

        while left < mid < right and 0 <= left and right < N:
            if abs(dts[left] + dts[mid] + dts[right]) < abs(sum(answers)):
                answers[0], answers[1], answers[2] = dts[left], dts[mid], dts[right]

            if dts[left] + dts[mid] + dts[right] < 0:
                right += 1
            elif dts[left] + dts[mid] + dts[right] > 0:
                left -= 1
            else:
                print(' '.join(map(str, answers)))
                return

    print(' '.join(map(str, answers)))


if __name__ == '__main__':
    solve()
