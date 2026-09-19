import copy
import sys

answer = sys.maxsize


def solution(cost, hint):
    last_stage = len(cost)
    cost = [[]] + cost
    hint = [[]] + hint
    myhint = [0] * (last_stage + 1)

    rec(cost, hint, last_stage, 1, 0, myhint)

    return answer


def rec(cost, hint, last_stage, current_stage, mycost, myhint):
    global answer

    if current_stage == last_stage:
        # 특정 스테이지의 힌트권의 갯수가 '힌트권 사용 수' 보다 많을 수 있음
        h = min(len(cost[current_stage]) - 1, myhint[current_stage])
        stage_cost = cost[current_stage][h]
        answer = min(answer, mycost + stage_cost)
        return;

    h = min(len(cost[current_stage]) - 1, myhint[current_stage])
    mycost += cost[current_stage][h]

    # 힌트권을 구매하지 않는경우
    rec(cost, hint, last_stage, current_stage + 1, mycost, myhint)

    # 힌트권을 구매하는 경우
    myhint2 = copy.deepcopy(myhint)
    hint_cost = hint[current_stage][0]
    for h in hint[current_stage][1:]:
        myhint2[h] += 1
    rec(cost, hint, last_stage, current_stage + 1, mycost + hint_cost, myhint2)
