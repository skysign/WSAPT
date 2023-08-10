class Solution:
    def maxProduct(self, dt: list[int]) -> int:
        dpL2R = [0 for _ in range(len(dt))]
        dpL2R[0] = dt[0]
        dpL2Rplus = [0 for _ in range(len(dt))]
        dpL2Rplus[0] = dt[0]

        dpR2L = [0 for _ in range(len(dt))]
        dpR2L[len(dt) -1] = dt[len(dt) -1]
        dpR2Lplus = [0 for _ in range(len(dt))]
        dpR2Lplus[len(dt) -1] = dt[len(dt) -1]

        for idx in range(1, len(dt)):
            if dpL2R[idx -1] == 0:
                dpL2R[idx] = dt[idx]
            else:
                dpL2R[idx] = dt[idx] * dpL2R[idx - 1]

            if dpL2Rplus[idx - 1] == 0:
                dpL2Rplus[idx] = dt[idx]
            else:
                if (dt[idx] > 0 and dpL2Rplus[idx - 1] > 0) or (dt[idx] < 0 and dpL2Rplus[idx - 1] < 0):
                    dpL2Rplus[idx] = dt[idx] * dpL2Rplus[idx - 1]
                elif dt[idx] < 0 and dt[idx -1] < 0:
                    dpL2Rplus[idx] = dt[idx] * dt[idx -1]
                else:
                    dpL2Rplus[idx] = dt[idx]

        for idx in range(len(dt)-2, -1, -1):
            if dpR2L[idx +1] == 0:
                dpR2L[idx] = dt[idx]
            else:
                dpR2L[idx] = dt[idx] * dpR2L[idx +1]

            if dpR2Lplus[idx - 1] == 0:
                dpR2Lplus[idx] = dt[idx]
            else:
                if (dt[idx] > 0 and dpR2Lplus[idx +1] > 0) or (dt[idx] < 0 and dpR2Lplus[idx +1] < 0):
                    dpR2Lplus[idx] = dt[idx] * dpR2Lplus[idx +1]
                elif dt[idx] < 0 and dt[idx +1] < 0:
                    dpR2Lplus[idx] = dt[idx] * dt[idx +1]
                else:
                    dpR2Lplus[idx] = dt[idx]

        answer = dt[0]

        for idx in range(len(dt)):
            L2R = -1 * 2.1 * (10 ** 9)
            L2Rplus = -1 * 2.1 * (10 ** 9)
            R2L = dpR2L[idx]
            R2Lplus = dpR2Lplus[idx]

            if idx -1 >= 0:
                L2R = dpL2R[idx-1]
                L2Rplus = dpL2Rplus[idx - 1]

            answer = max(
                answer,
                L2R,
                R2L,
                L2Rplus,
                R2Lplus,
                myConditionalExpression(L2R, R2L),
                myConditionalExpression(L2R, R2Lplus),
                myConditionalExpression(L2Rplus, R2L),
                myConditionalExpression(L2Rplus, R2Lplus)
            )

        answer = max( answer, dpL2R[len(dt) -1], dpL2Rplus[len(dt) -1] )

        for idx in range(len(dt)):
            answer = max(answer, dt[idx])

        return answer


def myConditionalExpression(a, b):
    if a == -1 * 2.1 * (10 ** 9):
        return b
    if b == -1 * 2.1 * (10 ** 9):
        return a

    if a == 0:
        return b
    if b == 0:
        return a

    return a * b