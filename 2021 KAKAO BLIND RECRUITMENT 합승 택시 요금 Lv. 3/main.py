from typing import List


def solution(n: int, s: int, a: int, b: int, fares: List):
    # INF = 100000 * n
    INF = -1
    map = [[INF for _ in range(n + 1)] for _ in range(n + 1)]

    for fr, to, fee in fares:
        map[fr][to] = fee
        map[to][fr] = fee

    for i in range(1, n + 1):
        map[i][i] = 0

    for mid in range(1, n + 1):
        for bgn in range(1, n + 1):
            for end in range(1, n + 1):
                if map[bgn][mid] != -1 and map[mid][end] != -1:
                    if map[bgn][end] == -1:
                        map[bgn][end] = map[bgn][mid] + map[mid][end]
                    elif map[bgn][end] > map[bgn][mid] + map[mid][end]:
                        map[bgn][end] = map[bgn][mid] + map[mid][end]

    answer = map[s][a] + map[s][b]
    for mid in range(1, n + 1):
        if map[s][mid] != -1 and map[mid][a] != -1 and map[mid][b] != -1:
            local_answer = map[s][mid] + map[mid][a] + map[mid][b]
            answer = min(answer, local_answer)

    return answer
