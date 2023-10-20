from typing import List
from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        rltDict = defaultdict(list)

        for s in strs:
            key = ''.join(sorted(s))
            rltDict[key].append(s)

        return list(rltDict.values())