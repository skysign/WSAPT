def solve():
    n1, n2 = input().split(' ')
    n1, n2 = int(n1), int(n2)
    if n1 < n2:
        print('<')
    elif n1 > n2:
        print('>')
    else:
        print('==')


if __name__ == '__main__':
    solve()