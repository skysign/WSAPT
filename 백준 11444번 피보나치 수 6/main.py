import sys
from collections import defaultdict


def solve():
    mod = 1000000007
    n = int(sys.stdin.readline().strip())
    memo = defaultdict(int)
    memo[0] = 0
    memo[1] = 1
    memo[2] = 1

    def fibo_memo(n):
        if n <= 2:
            return memo[n]
        elif memo[n] > 0:
            return memo[n]

        quotient, remainder = divmod(n, 2)
        if remainder == 0:
            pp = fibo_memo(quotient - 1)
            p = fibo_memo(quotient)
            memo[n] = ((2 * pp + p) * p) % mod
            return memo[n]
        else:
            p = fibo_memo(quotient + 1)
            pp = fibo_memo(quotient)
            memo[n] = (pp ** 2 + p ** 2) % mod
            return memo[n]

    print(fibo_memo(n))


if __name__ == '__main__':
    solve()
