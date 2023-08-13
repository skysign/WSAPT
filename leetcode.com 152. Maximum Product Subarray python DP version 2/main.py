from typing import List

class Solution:
    def maxProduct(self, dt: List[int]) -> int:
        MY_MIN = int(-1 * 2.1 * (10 ** 8))
        MY_MAX = int(2.1 * (10 ** 8))
        dpMin = [MY_MAX for _ in range(len(dt))]
        dpMax = [MY_MIN for _ in range(len(dt))]
        dpMin[0] = dpMax[0] = dt[0]
        answer = max(dpMin[0], dpMax[0])

        for idx in range(1, len(dt)):
            dpMin[idx] = min(dt[idx], dt[idx] * dpMin[idx - 1], dt[idx] * dpMax[idx - 1])
            dpMax[idx] = max(dt[idx], dt[idx] * dpMax[idx - 1], dt[idx] * dpMin[idx - 1])
            answer = max(answer, dpMax[idx])

        return answer


    def maxProduct_optmization1(self, dt: List[int]) -> int:
        MY_MIN = int(-1 * 2.1 * (10 ** 8))
        MY_MAX = int(2.1 * (10 ** 8))
        answer = MY_MIN

        # dpMin, dpMax 배열이 필요할 것 같지만,
        # recurrence relation에서 dp[idx-1] 만 사용하기 때문에,
        # 배열이 아니라, 변수 2개로 코드를 줄일 수 있다.
        dpMinPrev = dpMaxPrev = dt[0]
        answer = max(dpMinPrev, dpMaxPrev)

        for idx in range(1, len(dt)):
            dpMin = min(dt[idx], dt[idx] * dpMinPrev, dt[idx] * dpMaxPrev)
            dpMax = max(dt[idx], dt[idx] * dpMaxPrev, dt[idx] * dpMinPrev)

            answer = max(answer, dpMax)

            dpMinPrev = dpMin
            dpMaxPrev = dpMax

        return answer


    def maxProduct_optmization2(self, dt: List[int]) -> int:
        answer = int(-1 * 2.1 * (10 ** 8))

        dpMinPrev = dpMaxPrev = dt[0]
        answer = max(dpMinPrev, dpMaxPrev)

        # for idx in range(1.len(dt)):
        # 이렇게 index를 가지고 와서 접근해도, 문제를 푸는대 문제는 없지만,
        # data item을 직접 가지고 와도, 순서는 동일하다.
        # item을 직접 가지고 오는 것이, dt[index]하는 것보다 빠르다.
        # 이 문제의 recurrence relation은 idx-1만 필요하기 때문에, index가 필요없다.
        del dt[0]   # 제일 첫 번째 값음 dpMinPrev, dpMaxPrev의 첫번로 어싸인해서 47라인에서 처리 했으므로, 삭제한다.
        for item in dt:
            dpMin = min(item, item * dpMinPrev, item * dpMaxPrev)
            dpMax = max(item, item * dpMaxPrev, item * dpMinPrev)

            answer = max(answer, dpMax)

            dpMinPrev = dpMin
            dpMaxPrev = dpMax

        return answer