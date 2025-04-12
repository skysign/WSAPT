from typing import List


class Solution:
    def calcEquation(self, equations: List[List[str]], values: List[float], queries: List[List[str]]) -> List[float]:
        dicts = {}

        for i in range(len(equations)):
            d1, d2 = equations[i]
            v = values[i]

            if d1 not in dicts.keys():
                dicts[d1] = {}

            dicts[d1][d2] = v
            dicts[d1][d1] = 1

            if d2 not in dicts.keys():
                dicts[d2] = {}

            dicts[d2][d1] = 1 / v
            dicts[d2][d2] = 1

        answer = []

        for q1, q2 in queries:
            rtn = self.direct(dicts, q1, q2)

            if None != rtn:
                answer.append(rtn)
                continue

            rtn = self.dfs(dicts, q1, q2, 1, [])
            answer.append(rtn)

        return answer

    def direct(self, dicts, q1, q2):
        if q1 in dicts.keys():
            if q2 in dicts[q1].keys():
                return dicts[q1][q2]

        return None

    def dfs(self, dicts, bgn, end, v, visited: List[str]):
        if bgn not in dicts.keys():
            return -1

        for mid in dicts[bgn].keys():
            if mid not in visited:
                if mid == end:
                    v *= dicts[bgn][mid]
                    return v
                else:
                    rtn = self.dfs(dicts, mid, end, v * dicts[bgn][mid], visited + [bgn])
                    if rtn != -1:
                        return rtn

        return -1
