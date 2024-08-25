import sys

def solve():
    N = int(sys.stdin.readline().strip())
    dict = {}

    for _ in range(N):
        parent, left, right = sys.stdin.readline().strip().split(' ')
        dict[parent] = [left, right]

    answer = pre_order(dict, 'A')
    print(answer)
    answer = in_order(dict, 'A')
    print(answer)
    answer = post_order(dict, 'A')
    print(answer)


def pre_order(dict, parent):
    if parent == '.':
        return ''

    left, right = dict[parent]

    return parent + pre_order(dict, left) + pre_order(dict, right)

def in_order(dict, parent):
    if parent == '.':
        return ''

    left, right = dict[parent]

    return in_order(dict, left) + parent + in_order(dict, right)

def post_order(dict, parent):
    if parent == '.':
        return ''

    left, right = dict[parent]

    return post_order(dict, left) + post_order(dict, right) + parent

if __name__ == '__main__':
    solve()