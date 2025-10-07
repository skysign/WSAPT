from typing import List


class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        rt_max = len(numbers) - 1
        for lt in range(len(numbers) - 1):
            t = target - numbers[lt]
            rt = self.bs(numbers, t, lt + 1, rt_max)
            if rt > 0:
                return [lt + 1, rt + 1]

        return []

    def bs(self, numbers: List[int], t: int, lt: int, rt: int) -> int:
        while lt <= rt:
            mid = (lt + rt) // 2

            if numbers[mid] == t:
                return mid

            if numbers[mid] < t:
                lt = mid + 1
            else:  # t < numbers[rt]
                rt = mid - 1

        return -1
