from collections import defaultdict
from typing import Dict, List
from itertools import combinations


def solution(dices):
    answer = []
    dict: Dict = {}

    dice_indexes = [i for i in range(len(dices))]

    # n 개 에서 n/2 개를 고르는 조합을 만들고,
    dice_combs = combinations(dice_indexes, int(len(dices) / 2))
    dice_combs = list(dice_combs)

    for dice_comb in dice_combs:
        # 선택된 주사위의 모든 경우의 수를 찾고,
        all_dice_combs = get_all_combs_of_dices(dices, dice_comb)
        dict[tuple(dice_comb)] = all_dice_combs

    max_wins = 0

    for dice_comb in dice_combs:
        A_dices = dice_comb
        B_dices = tuple(set(dice_indexes).difference(A_dices))
        new_A_wins = get_wins(dict[A_dices], dict[B_dices])

        # 이기는 경우가 가장 많은 주사위 조합을 선택한다.
        if new_A_wins > max_wins:
            max_wins = new_A_wins
            answer = list(A_dices)

    answer = list(map(lambda x: x + 1, answer))

    return answer


def get_all_combs_of_dices(dices: List[int], dice_comb: List[List[int]]):
    inputs: Dict = {}
    inputs[0] = 1
    outputs: Dict = {}

    for dice_index in dice_comb:
        dice = dices[dice_index]

        for number in inputs.keys():
            for dice_number in dice:
                key = number + dice_number

                if key in outputs.keys():
                    outputs[key] += inputs[number]
                else:
                    outputs[key] = inputs[number]

        inputs = outputs
        outputs = {}

    return inputs


# 각 주사위 조합의 모든 경우의 수를 서로 비교, 이기는 수를 세어본다
def get_wins(A_dice_all_combs: Dict, B_dice_all_combs: Dict):
    rtn = 0

    for a_number in A_dice_all_combs.keys():
        for b_number in B_dice_all_combs.keys():
            if a_number > b_number:
                rtn += (A_dice_all_combs[a_number] * B_dice_all_combs[b_number])

    return rtn
