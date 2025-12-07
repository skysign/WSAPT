from typing import List


class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        if k == 0:
            return

        l = len(nums)
        k = k % l

        # nums에 rotate를 시키는 구현, 정답 판정은 되지만, 속도가 느림
        # for _ in range(k):
        #     v = nums.pop()
        #     nums.insert(0, v)

        tmp = [0] * k
        for i in range(k):
            tmp[i] = nums[l - k + i]

        for i in range(l - k -1, -1, -1):
            nums[i + k] = nums[i]

        for i in range(k):
            nums[i] = tmp[i]
