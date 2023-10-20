from typing import List

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        dict = {}

        for num in nums:
            dict[num] = 0

        maxConsecutive = 0

        for key in dict.keys():
            if dict.__contains__(key -1) == False:
                tmp = self.findConsecutive(dict, key, 0)
                maxConsecutive = max(maxConsecutive, tmp)

        return maxConsecutive

    def findConsecutive(self, dict, key, count):
        if dict.__contains__(key):
            count += 1
            return self.findConsecutive(dict, key+1, count)

        return count