from typing import List


def solution(temperature, t1, t2, a, b, onboard):
    # 온도를 리스트의 인덱스로 사용하기 위해서는, 온도가 < 0 은 경우가 없어야 한다.
    # 10도씨 모두 증가 시켜서, 최저 온도를 0으로 만든다.
    # temperature, t1, t2 모두 10도씩 올렸기 때문에 문제의 결과에는 영향이 없다.
    tmpr, t1, t2 = temperature + 10, t1 + 10, t2 + 10

    TMPR_MAX = 50
    dt = 1 if tmpr < t1 else -1
    dp: List[List] = [[10 ** (2 + 3) for _ in range(len(onboard) + 1)] for _ in range(TMPR_MAX + 1)]

    # for t in range(TMPR_MAX + 1):
    dp[tmpr][0] = 0

    # dp[ta][i], dp[tb][i+1] 의 관계
    # ta온도에서 tb 온도가 되기 위해서 사용한 에너지 가 dp[i+1][tb]에 저장된다.
    tmpr_lowest, tmpr_highest, = min(t1, tmpr), max(t2, tmpr)

    for i in range(len(onboard)):
        tmpr_min, tmpr_max = tmpr_lowest, tmpr_highest

        if onboard[i]:
            tmpr_min, tmpr_max = t1, t2

        for t in range(tmpr_min, tmpr_max + 1):
            nt = t + dt
            if 0 <= nt <= TMPR_MAX:
                dp[nt][i + 1] = min(dp[nt][i + 1], dp[t][i] + a)

            if t != tmpr:
                dp[t][i + 1] = min(dp[t][i + 1], dp[t][i] + b)
            else:
                dp[t][i + 1] = min(dp[t][i + 1], dp[t][i])

            nt = t + (dt * -1)
            if 0 <= nt <= TMPR_MAX:
                dp[nt][i + 1] = min(dp[nt][i + 1], dp[t][i])

    answer = 10 ** (2 + 3)

    for t in range(TMPR_MAX +1):
        answer = min(answer, dp[t][len(onboard)])

    return answer
