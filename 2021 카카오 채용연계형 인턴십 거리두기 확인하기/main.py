# 2021 카카오 채용연계형 인턴십 거리두기 확인하기 Python
#
# 유튜브 문제 풀이: https://youtu.be/CSmCku0lDCQ
#
# 파이썬 소스: https://bit.ly/3PBrxZ9
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/81302#fn1

def solution(places):
    answer = []

    for place55 in places:
        answer.append(get_answer(place55))

    return answer


def get_answer(place):
    pijs = []

    for i in range(5):
        for j in range(5):
            if place[i][j] == "P":
                pijs.append([i, j])

    for pij in pijs:
        visited = [[False for _ in range(5)] for _ in range(5)]
        if 0 == bfs([pij], place, visited, depth=2):
            return 0

    return 1


def bfs(queue, place, visited, depth):
    dijs = [[1, 0], [0, 1], [-1, 0], [0, -1]]
    depth -= 1
    new_queue = []

    while len(queue) > 0:
        si, sj = queue.pop(0)
        visited[si][sj] = True

        for dij in dijs:
            di, dj = dij
            dsti = si + di
            dstj = sj + dj

            if 0 <= dsti < 5 and 0 <= dstj < 5 and visited[dsti][dstj] == False:
                if place[dsti][dstj] == "P":
                    return 0
                elif place[dsti][dstj] == "O":
                    new_queue.append([dsti, dstj])

    if depth > 0:
        return bfs(new_queue, place, visited, depth)

    return 1
