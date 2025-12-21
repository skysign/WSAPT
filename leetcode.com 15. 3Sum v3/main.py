from typing import List


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        answer = set()

        for ilt in range(len(nums)):
            imid, irt = ilt + 1, len(nums) - 1

            while imid < irt:
                sum3 = nums[ilt] + nums[imid] + nums[irt]

                if sum3 == 0:
                    answer.add((nums[ilt], nums[imid], nums[irt]))
                    imid += 1
                    irt -= 1
                elif sum3 < 0:
                    imid += 1
                else: # sum3 > 0:
                    irt -=1

        return [list(item) for item in list(answer)]
