import copy
from collections import deque, defaultdict
import heapq


def TCM_grouping(N, board_TCM, board_sinang, board_group, group_cnt, sr, sc):
    drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]
    queue = deque([(board_TCM[sr][sc], sr, sc)])
    heap = [(-board_sinang[sr][sc], sr, sc)]
    group = [(sr, sc)]
    board_group[sr][sc] = group_cnt

    while queue:
        tcm, sr, sc = queue.popleft()
        for dr, dc in drc:
            nr, nc = sr + dr, sc + dc
            if 1 <= nr <= N and 1 <= nc <= N:
                if board_group[nr][nc] == 0 and board_TCM[nr][nc] == tcm:
                    board_group[nr][nc] = group_cnt
                    heapq.heappush(heap, (-board_sinang[nr][nc], nr, nc))
                    group.append((nr, nc))
                    queue.append((tcm, nr, nc))

    return heapq.heappop(heap), group


def solve():
    N, T = map(int, input().split())

    board_TCM = [[set() for _ in range(N + 1)] for _ in range(N + 1)]
    board_sinang = [[0] * (N + 1) for _ in range(N + 1)]

    for r in range(1, N + 1):
        line = input()
        for c in range(1, N + 1):
            board_TCM[r][c].add(line[c - 1])

    for r in range(1, N + 1):
        row = list(map(int, input().split()))
        for c in range(1, N + 1):
            board_sinang[r][c] = row[c - 1]

    #  T는 민트를, C는 초코를, M은 우유
    tcm_oders = {}
    tcm_oders[('T',)] = 1
    tcm_oders[('C',)] = 1
    tcm_oders[('M',)] = 1
    tcm_oders[('C', 'M')] = 2
    tcm_oders[('M', 'T')] = 2
    tcm_oders[('C', 'T')] = 2
    tcm_oders[('C', 'M', 'T')] = 3
    # 이후 T일 동안, 하루는 아침, 점심, 저녁의 순서로 아래와 같은 과정이 진행됩니다.
    for _ in range(T):
        board_group = [[0] * (N + 1) for _ in range(N + 1)]

        # 1. 아침시간
        for sr in range(1, N + 1):
            for sc in range(1, N + 1):
                board_sinang[sr][sc] += 1

        # 2. 점심시간
        group_cnt = 1
        leaders = []
        groups = {}
        for sr in range(1, N + 1):
            for sc in range(1, N + 1):
                if board_group[sr][sc] == 0:
                    # 그룹 내에서는 대표자 한 명을 선정합니다.
                    [_, leader_r, leader_c], group_local = TCM_grouping(N, board_TCM, board_sinang, board_group, group_cnt, sr, sc)
                    groups[group_cnt] = group_local
                    leaders.append((leader_r, leader_c))

                    # 대표자를 제외한 그룹원들은 각자 신앙심을 1씩 대표자에게 넘깁니다. 대표자의 신앙심은 그룹원 수 −1만큼 추가되고, 나머지 그룹원은 1씩 감소합니다.
                    for (follower_r, follower_c) in groups[group_cnt]:
                        if (leader_r, leader_c) != (follower_r, follower_c):
                            board_sinang[follower_r][follower_c] -= 1

                    board_sinang[leader_r][leader_c] += (len(groups[group_cnt]) - 1)
                    group_cnt += 1

        # 3. 저녁 시간
        heap_leaders = []
        drc = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        for (leader_r, leader_c) in leaders:
            tcm = list(board_TCM[leader_r][leader_c])
            tcm.sort()
            tcm = tuple(tcm)
            tcm_order = tcm_oders[tcm]
            heapq.heappush(heap_leaders, (tcm_order, -board_sinang[leader_r][leader_c], leader_r, leader_c))

        defense_state = [[False] * (N + 1) for _ in range(N + 1)]

        while heap_leaders:
            _, _, leader_r, leader_c = heapq.heappop(heap_leaders)

            if defense_state[leader_r][leader_c]:
                continue

            leader_tcm = board_TCM[leader_r][leader_c]
            B = board_sinang[leader_r][leader_c]
            board_sinang[leader_r][leader_c] = 1
            direction = B % 4
            x_ganjeol = B - 1
            sr, sc = leader_r, leader_c
            for length in range(1, N):
                nr, nc = sr + (length * drc[direction][0]), sc + (length * drc[direction][1])
                if 1 <= nr <= N and 1 <= nc <= N and x_ganjeol > 0:
                    if leader_tcm == board_TCM[nr][nc]:
                        continue

                     # x>y이면 강한 전파에 성공합니다.
                    if x_ganjeol > board_sinang[nr][nc]:
                        x_ganjeol -= (board_sinang[nr][nc] + 1)
                        board_sinang[nr][nc] += 1
                        # 전파 대상은 전파자의 사상에 완전히 동화되어, 전파자의 신봉 음식과 동일한 음식을 신봉하게 됩니다.
                        board_TCM[nr][nc] = copy.deepcopy(leader_tcm)
                    # x≤y이면 약한 전파에 성공합니다.
                    else:
                        # 약한 전파에 성공한다면, 전파 대상은 전파자가 전파한 음식의 모든 기본 음식에도 관심을 가지게 됩니다. 즉, 기존에 관심을 가지고 있던 기본 음식들과 전파자가 관심을 가지고 있는 기본 음식을 모두 합친 음식을 신봉하게 됩니다.
                        board_TCM[nr][nc] = board_TCM[nr][nc].union(leader_tcm)
                        # 대상의 신앙심은 x만큼 증가합니다.
                        board_sinang[nr][nc] += x_ganjeol
                        # 이 경우 전파자는 간절함이 0이 되고 더이상 전파를 진행하지 않지만,
                        x_ganjeol = 0

                    defense_state[nr][nc] = True
                else:
                    break

        answers = defaultdict(int)
        tcm_oders_answers = {}
        tcm_oders_answers[('T',)] = 1
        tcm_oders_answers[('C',)] = 2
        tcm_oders_answers[('M',)] = 3
        tcm_oders_answers[('C', 'M')] = 4
        tcm_oders_answers[('M', 'T')] = 5
        tcm_oders_answers[('C', 'T')] = 6
        tcm_oders_answers[('C', 'M', 'T')] = 7
        for sr in range(1, N + 1):
            for sc in range(1, N + 1):
                tcm = list(board_TCM[sr][sc])
                tcm.sort()
                tcm = tuple(tcm)
                key = tcm_oders_answers[tcm]
                answers[key] += board_sinang[sr][sc]

        answer = str(answers[7])
        for key in range(6, 0, -1):
            answer += (' ' + str(answers[key]))
        print(answer)

if __name__ == '__main__':
    solve()
