supo0 = [1, 2, 3, 4, 5]
supo1 = [2, 1, 2, 3, 2, 4, 2, 5]
supo2 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
supos = [supo0, supo1, supo2]

supos_score = [0 for _ in range(len(supos))]

def solution(answers):
    for idx in range(len(answers)):
        for supo_idx in range(len(supos)):
            if answers[idx] == supos[supo_idx][idx % len(supos[supo_idx])]:
                supos_score[supo_idx] += 1

    max_score = max(supos_score)
    answer = []

    for supo_idx in range(len(supos)):
        if max_score == supos_score[supo_idx]:
            answer.append(supo_idx +1)

    return answer