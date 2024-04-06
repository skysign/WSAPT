from typing import List


def solution(temperature, t1, t2, a, b, onboard):
    # 온도를 리스트의 인덱스로 사용하기 위해서는, 온도가 < 0 은 경우가 없어야 한다.
    # 10도씨 모두 증가 시켜서, 최저 온도를 0으로 만든다.
    # temperature, t1, t2 모두 10도씩 올렸기 때문에 문제의 결과에는 영향이 없다.
    tmpr, t1, t2 = temperature + 10, t1 + 10, t2 + 10

    # 최대 온도는 50입니다.
    TMPR_MAX = 50
    # 공조를 해서 온도를 변화시켰을 때, delta temperature, 줄여서 dt 값을 계산합니다.
    dt = 1 if tmpr < t1 else -1
    # 분을 column으로 온도를 row로, 2차원 배열 dp 리스트를 만들어 줍니다.
    dp: List[List] = [[10 ** (2 + 3) for _ in range(len(onboard) + 1)] for _ in range(TMPR_MAX + 1)]
    # 여기서 기본값을 채울 때, 10**2 는 a,b 의 최대값 100 에서 왔구요,
    # 10**3은 onbaord의 최대길이 1000 에서 왔습니다.
    # 소비전력을 최대로 사용한다고 했을 때, 10**5 가 되기 때문에, 초기값을 10 ** (2+3) 으로 잡았습니다.
    # 초기값이 최대값이기 때문에, min() 함수를 사용해서 보다 구현을 간단하게 할 수 있습니다. <- 간단하게 구현할 수 있는 이유는 뒤에서 설명하겠습니다.
    # dp[ta][i], dp[tb][i+1] 의 관계는
    # ta온도에서 tb 온도가 되기 위해서 사용한 에너지 가 dp[tb][i+1]에 저장된다.
    # 따라서, len(onboard)길이 보다 1크게 리스트를 만들어야 합니다.

    # 문제를 풀기 시작 할 때, dp 배열에 0은 부분은 dp[tmpr][0] 뿐입니다.
    # 실외 온도와 실내 온도가 같기 때문에, dp[tmpr][0] 에서부터 온도 변화가 생깁니다.
    dp[tmpr][0] = 0

    # 사람이 탑승하지 않은 상태에서, 우리가 다뤄야할 온도 범위 입니다.
    tmpr_lowest, tmpr_highest, = min(t1, tmpr), max(t2, tmpr)

    for i in range(len(onboard)):
        # 사람이 탑승하지 않은 상태에서 온도 변화 범위를
        # tmpr_min, tmpr_max의 기본값으로 사용합니다.
        tmpr_min, tmpr_max = tmpr_lowest, tmpr_highest

        if onboard[i]:
            tmpr_min, tmpr_max = t1, t2
            # 사람이 탑승했다면, 온도 범위는 t1, t2 이내여야 합니다.

        for t in range(tmpr_min, tmpr_max + 1):
            # 온도가 변하는 공조를 해서, 온도가 1증가/감소 하는 nt가 되는 경우 입니다.
            nt = t + dt
            if 0 <= nt <= TMPR_MAX:  # dt가 -1/+1 모두 가능하기 때문에, 리스트의 범위를 벗어나는 것을 막아 줍니다.
                dp[nt][i + 1] = min(dp[nt][i + 1], dp[t][i] + a)
                # 온도가 변하는 공조를 했기 때문에, 기존에 사용한 전력(dp[t][i])에 온도 변화 공조에 필요한 소비전력 a를 더해 줍니다.
                # dp의 초기값은 모두 최대 소비전력 (10**5)로 채워저 있기 때문에,
                # 초기값을 가지고 있는 부분은, dp[t][i] + a 값이 되구요,
                # 그 이후부터는 min() 함수의 결과에 따라서, 작은 값을 dp 리스트에 저장합니다.

            # 온도가 변하지 않는 공조를 하는 경우 입니다.
            if t != tmpr:
                dp[t][i + 1] = min(dp[t][i + 1], dp[t][i] + b)
                # 온도를 유지하는 공조에 필요한 소비전력 +b 가 사용됩니다.
            else:  # tmpr과 t온도가 같다면, 소비전력 없이, 현재 온도를 유지 할 수 있습니다.
                dp[t][i + 1] = min(dp[t][i + 1], dp[t][i])

            # 마지막으로, 공조를 전혀 하지 않는 경우 입니다.
            # dt에 -1를 곱해서, dt 값의 반대 방향으로 온도가 증가/감소합니다.
            nt = t + (dt * -1)
            if 0 <= nt <= TMPR_MAX:  # dt가 -1/+1 모두 가능하기 때문에, 리스트의 범위를 벗어나는 것을 막아 줍니다.
                dp[nt][i + 1] = min(dp[nt][i + 1], dp[t][i])
                # 이전까지 소비한 소비전력의 합 그대로 min 값에 인자로 넣어줍니다.

    answer = 10 ** (2 + 3)

    for t in range(tmpr_lowest, tmpr_highest + 1):
        # 소비된 전력의 최소값을 찾습니다.
        answer = min(answer, dp[t][len(onboard)])

    return answer
