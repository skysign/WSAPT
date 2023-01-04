SHEEP = 0
WOLF = 1

# 2022 카카오 신입 공채 1차 온라인 코딩테스트 양과 늑대 Python
#
# 유튜브 문제 풀이: https://youtu.be/6Hxf7zjSA-g
#
# 파이썬소스: http://bit.ly/3vK7IDz
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/92343

def solution(info, edges):
    tree = {}

    for edge in edges:
        parent, child = edge

        if not tree.__contains__(parent):
            tree[parent] = []

        tree[parent].append(child)

    answer = travel(tree, info, 0, [], 0, 0)

    return answer


def travel(tree, info, visit, visitable, sheep, wolf):
    if info[visit] == SHEEP:
        sheep += 1
    else:
        wolf += 1

    if sheep <= wolf:
        return sheep

    max_sheep = sheep

    if tree.__contains__(visit):
        visitable += tree[visit]

    for new_visit in visitable:
        new_visitable = list(visitable)
        new_visitable.remove(new_visit)
        local_sheep = travel(tree, info, new_visit, new_visitable, sheep, wolf)
        max_sheep = max(max_sheep, local_sheep)

    return max_sheep
