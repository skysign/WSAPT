from typing import List


class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        answer, length = 0, len(nums)
        nums.append(-1)
        to = 0

        while to < length:
            if nums[to] == val:
                # overwrite nums[to]
                for idx in range(to, length):
                    nums[idx] = nums[idx + 1]
                answer += 1
            else:
                to += 1

        nums.pop(length)

        return length - answer


