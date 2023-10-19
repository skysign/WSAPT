from typing import List

class Solution:
    def containsDuplicate2(self, nums: List[int]) -> bool:
        myset = set()

        for num in nums:
            if num in myset:
                return True
            else:
                myset.add(num)

        return False

    def containsDuplicate2(self, nums: List[int]) -> bool:
        mydict = {}
        for num in nums:
            if mydict.keys().__contains__(num):
                return True

            mydict[num] = 0

        return False

    def containsDuplicate1(self, nums: List[int]) -> bool:
        return len(set(nums)) != len(nums)