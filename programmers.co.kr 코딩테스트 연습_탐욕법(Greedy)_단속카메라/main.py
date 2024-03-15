from typing import List

def solution(routes: List[List]):
    if len(routes) == 0:
        return 0

    routes.sort()
    answers: List = [routes.pop(0)]

    for car_in, car_out in routes:
        prev_in, prev_out = answers.pop()

        if prev_in <= car_out <= prev_out \
            or car_in <= prev_out <= car_out:
            answers.append([max(prev_in, car_in), min(prev_out, car_out)])
        else:
            answers.append([prev_in, prev_out])
            answers.append([car_in, car_out])

    return len(answers)