import sys


def solve():
    # input() 사용하면, 시간초과 발생함
    N = int(sys.stdin.readline().strip())
    ans = set()

    for _ in range(N):
        # input() 사용하면, 시간초과 발생함
        dt = sys.stdin.readline().strip()
        op = ''
        v = 0

        if dt.__contains__(' '):
            op, v = dt.split(' ')
            v = int(v)
        else:
            op = dt

        if op == 'add':
            ans.add(v)
        elif op == 'remove':
            # if v in ans:
            #     ans.remove(v)
            ans.discard(v)
        elif op == 'check':
            p = 1 if v in ans else 0
            print(p)
        elif op == 'toggle':
            if v in ans:
                ans.remove(v)
            else:
                ans.add(v)
        elif op == 'all':
            t = [i for i in range(1, 21)]
            ans = set(t)
        elif op == 'empty':
            ans = set()

if __name__ == '__main__':
    solve()