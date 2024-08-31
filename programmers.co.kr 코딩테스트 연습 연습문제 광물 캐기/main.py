import copy
from typing import List, Deque
from collections import deque
import sys


def solution(picks: List[int], minerals: List[str]):
    minerals: Deque = deque(minerals)
    piros = {}
    piros[0] = {'diamond': 1, 'iron': 1, 'stone': 1}
    piros[1] = {'diamond': 5, 'iron': 1, 'stone': 1}
    piros[2] = {'diamond': 25, 'iron': 5, 'stone': 1}

    answer = [sys.maxsize]
    dfs(picks, minerals, piros, 0, answer)
    answer = answer[0]
    return answer


def dfs(picks: List[int], minerals: Deque, piros, prev_piro, answer):
    for pick in [0, 1, 2]:
        if picks[pick] > 0:
            new_picks = copy.deepcopy(picks)
            new_picks[pick] -= 1

            local_minerals = copy.deepcopy(minerals)
            local_piro = prev_piro

            for _ in range(5):
                if len(local_minerals) > 0:
                    piro = piros[pick][local_minerals.popleft()]
                    local_piro += piro
                else:
                    answer[0] = min(answer[0], local_piro)
                    break

            if sum(new_picks) > 0 and len(local_minerals) > 0:
                dfs(new_picks, local_minerals, piros, local_piro, answer)
            else:
                answer[0] = min(answer[0], local_piro)
