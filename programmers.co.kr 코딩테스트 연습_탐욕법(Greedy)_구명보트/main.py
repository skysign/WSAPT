from typing import List

def solution(people: List, limit: int):
    people.sort()
    answer = 0
    idx_left, idx_right = 0, len(people) -1

    while idx_left < idx_right:
        if people[idx_right] > limit - people[idx_left]:
            idx_right -= 1
            answer += 1
        else:
            idx_left += 1
            idx_right -= 1
            answer += 1

    if idx_left == idx_right:
        answer += 1

    return answer
