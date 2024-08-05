import sys


def solve():
    listen, see = map(int, sys.stdin.readline().strip().split(' '))
    s1, s2 = set(), set()

    for _ in range(listen):
        dt = sys.stdin.readline().strip()
        s1.add(dt)

    for _ in range(see):
        dt = sys.stdin.readline().strip()
        s2.add(dt)

    s3 = s1.intersection(s2)
    answer = list(s3)
    answer.sort()

    print(len(answer))

    for a in answer:
        print(a)


if __name__ == '__main__':
    solve()