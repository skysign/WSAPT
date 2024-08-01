def solve():
    v = int(input())

    for n in range(1, 10):
        print('%d * %d = %d' % (v, n, v*n))

if __name__ == '__main__':
    solve()