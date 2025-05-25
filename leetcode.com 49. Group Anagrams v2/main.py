from typing import List
from collections import defaultdict


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        rlt = defaultdict(list)

        for str in strs:
            key = ''.join(sorted(list(str)))
            rlt[key].append(str)

        return list(rlt.values())
