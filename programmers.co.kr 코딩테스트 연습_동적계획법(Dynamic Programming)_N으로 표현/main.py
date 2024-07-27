from typing import List, Set


def solution(N, number):
    if N == number:
        return 1

    dp: List[Set] = [set() for _ in range(9)]

    strN = '{:d}'.format(N)

    for i in range(1, len(dp)):
        st = dp[i]
        st.add(int(''.join(([strN] * i))))

    for i in range(2, 9):
        for j in range(1, i):
            for v1 in dp[j]:
                for v2 in dp[i-j]:
                    dp[i].add(v1 + v2)
                    dp[i].add(v1 - v2)
                    dp[i].add(v1 * v2)

                    if v2 != 0:
                        dp[i].add(v1 // v2)

        if number in dp[i]:
            return i
            break

    return -1
