import sys


def solve():
    input = sys.stdin.readline
    dts = [0] + list(map(int, input().strip().split()))
    dts.pop()

    power = [[0 for _ in range(5)] for _ in range(5)]

    # 만약 같은 지점을 한번 더 누른다면, 그때는 1의 힘을 사용하게 된다.
    power[0][0] = 1
    power[1][1] = 1
    power[2][2] = 1
    power[3][3] = 1
    power[4][4] = 1

    # 만약, 중앙에 있던 발이 다른 지점으로 움직일 때, 2의 힘을 사용하게 된다.
    power[0][1] = 2
    power[0][2] = 2
    power[0][3] = 2
    power[0][4] = 2

    # 인접한 지점으로 움직일 때는 3의 힘을 사용하게 된다.
    power[1][2] = 3
    power[1][4] = 3
    power[2][1] = 3
    power[2][3] = 3
    power[3][2] = 3
    power[3][4] = 3
    power[4][1] = 3
    power[4][3] = 3

    # 반대편으로 움직일때는 4의 힘을 사용하게 된다. (위쪽에서 아래쪽으로, 또는 오른쪽에서 왼쪽으로).
    power[1][3] = 4
    power[2][4] = 4
    power[3][1] = 4
    power[4][2] = 4

    # 입력되는 수열의 길이는 100,000을 넘지 않는다.
    # 위의 문장에 따라서, 최대로 사용하는 힘 4와 100,000을 곱해주고, 1을 더해준다.
    MY_MAX = 4 * 100000 + 1

    dp = [[[MY_MAX, MY_MAX, MY_MAX, MY_MAX, MY_MAX] for _ in range(5)] for _ in range(len(dts))]

    # 아무 말판도 밟지 않은 상태
    dp[0][0][0] = 0
    # 첫번째, 발판의 순서
    # 두번째, 왼발을 이동해서 밟는 경우,
    # 세번째, 오른발을 이동해서 밟는 경우,
    # 배열의 값은, 현재 발판의 순서까지 밟는대 소모된 최소의 힙의 함

    for i in range(1, len(dts)):
        v = dts[i]

        # 왼발을 움직여서 v를 밟는다.
        for l in range(5):
            for r in range(5):
                dp[i][v][r] = min(dp[i][v][r], dp[i - 1][l][r] + power[l][v])

        # 오른발 이동해서, 밟기
        for l in range(5):
            for r in range(5):
                dp[i][l][v] = min(dp[i][l][v], dp[i - 1][l][r] + power[r][v])


    answer = MY_MAX
    i = len(dts) -1
    for l in range(5):
        for r in range(5):
            answer = min(answer, dp[i][l][r])

    print(answer)

if __name__ == '__main__':
    solve()
