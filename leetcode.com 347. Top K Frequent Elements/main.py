from typing import List
from collections import defaultdict

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        rltDict = defaultdict(int)

        for num in nums:
            rltDict[num] += 1

        sortedDict = sorted(rltDict.items(), key=lambda item: item[1], reverse=True)
        rlt = []

        for idx in range(k):
            rlt.append(sortedDict[idx][0])

        return rlt