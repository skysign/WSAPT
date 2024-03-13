from typing import List

def solution(targets: List[List]):
    targets.sort()
    answers: List = [targets[0]]
    targets.pop(0)

    for s, e in targets:
        s_prev, e_prev = answers.pop()
        overlapped: bool = False

        if s == s_prev or e == e_prev:
            overlapped = True
        elif s < e_prev < e:
            overlapped = True
        elif s_prev < e < e_prev:
            overlapped = True

        if overlapped:
            answers.append([max(s, s_prev), min(e, e_prev)])
        else:
            answers.append([s_prev, e_prev])
            answers.append([s, e])

    return len(answers)