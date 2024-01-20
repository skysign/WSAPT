from typing import List
from collections import deque

max_round = 0
N = 0

def solution(coin, cards):
    global max_round, N
    N = len(cards)

    round(cards[:int(N / 3)], deque(cards[int(N / 3):]), [], coin, n_round=1)

    return max_round


def round(my_cards: List[int], remained_cards: deque, available_cards: List[int], coin: int, n_round):
    global max_round, N

    max_round = max(max_round, n_round)

    if len(remained_cards) > 0:
        available_cards.append(remained_cards.popleft())
        available_cards.append(remained_cards.popleft())
    else:
        return

    # my_cards만 가지고 다음 round로 갈 수 있음
    if len(my_cards) >= 2:
        for card1 in my_cards:
            if N + 1 - card1 in my_cards:
                my_cards.remove(card1)
                my_cards.remove(N + 1 - card1)
                round(my_cards, remained_cards, available_cards, coin, n_round + 1)
                return

    if len(my_cards) > 0 and len(available_cards) > 0 and coin > 0:
        for card1 in my_cards:
            if N + 1 - card1 in available_cards:
                my_cards.remove(card1)
                available_cards.remove(N + 1 - card1)
                round(my_cards, remained_cards, available_cards, coin - 1, n_round + 1)
                return

    if len(available_cards) > 0 and coin >= 2:
        for card1 in available_cards:
            if N + 1 - card1 in available_cards:
                available_cards.remove(card1)
                available_cards.remove(N + 1 - card1)
                round(my_cards, remained_cards, available_cards, coin - 2, n_round + 1)
                return
