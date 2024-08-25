import sys


def solve():
    A, B, C = map(int, sys.stdin.readline().strip().split(' '))

    v = mypower(A, B, C)
    print(v % C)


def mypower(A, b, C):
    if b == 1:
        return A % C

    quotient = b // 2
    v = mypower(A, quotient, C)

    if b % 2 == 0:
        v = (v * v) % C
    else:
        v = (v * v * A) % C

    return v


if __name__ == '__main__':
    solve()
