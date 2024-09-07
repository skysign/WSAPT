import sys
sys.setrecursionlimit(11000)
from collections import defaultdict


def solve():
    n = int(sys.stdin.readline().strip())
    edges = defaultdict(list)

    for _ in range(n - 1):
        parent, child, length = map(int, sys.stdin.readline().strip().split(' '))
        edges[parent].append([child, length])

    answer = [0]
    dfs(1, answer, edges)

    print(answer[0])


def dfs(parent, answer, edges):
    local_answers = []

    for child, length in edges[parent]:
        local_answers.append(length + dfs(child, answer, edges))

    if len(local_answers) >= 2:
        local_answers.sort(reverse=True)
        local_answer = sum(local_answers[:2])
        answer[0] = max(answer[0], local_answer)

    if len(local_answers) >= 1:
        answer[0] = max(answer[0], local_answers[0])
        return local_answers[0]

    return 0


if __name__ == '__main__':
    solve()
