import sys


def solve():
    N, r, c = map(int, sys.stdin.readline().strip().split(' '))
    length = 2 ** N

    answer = rec(length, length // 2, r, c, 0)
    print(answer)


def rec(length, half, r, c, answer):
    if half == 1:
        if r == 0 and c == 0:
            return answer
        if r == 0 and c == 1:
            return answer + 1
        if r == 1 and c == 0:
            return answer + 2

        return answer + 3

    br = r >= half
    bc = c >= half

    if br:
        answer += (half * half * 2)
        r -= half

    if bc:
        answer += (half * half)
        c -= half

    return rec(length, half // 2, r, c, answer)


if __name__ == '__main__':
    solve()