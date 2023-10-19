from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        mydict = {}

        for idx in range(len(nums)):
            num = nums[idx]
            mydict[num] = idx

        for idx in range(len(nums)):
            num2 = nums[idx]
            numFind = target - num2

            if mydict.__contains__(numFind):
                if (numFind == num2) and (idx == mydict[numFind]):
                    continue

                idx0 = min(mydict[numFind], idx)
                idx1 = max(mydict[numFind], idx)

                return [idx0, idx1]