from typing import List
from itertools import product


def solution(users: List[List[int]], emoticons: List[List[int]]):
    discounts = list(product([10, 20, 30, 40], repeat=len(emoticons)))
    answer = []

    for discount in discounts:
        local_subscriber = 0
        local_sell = 0

        for user in users:
            local_user_subscriber = False
            local_user_sell = 0
            user_dis, user_sell_limit = user

            for idx in range(len(emoticons)):
                emo_dis, emo_price = discount[idx], emoticons[idx]
                if user_dis <= emo_dis:
                    local_user_sell += (emo_price * (100 - emo_dis) // 100)

                if local_user_sell >= user_sell_limit:
                    local_user_subscriber = True
                    break

            if local_user_subscriber:
                local_subscriber += 1
            else:
                local_sell += local_user_sell

        answer.append([local_subscriber, local_sell])
        answer.sort(reverse=True)
        answer = [answer[0]]

    return answer[0]
