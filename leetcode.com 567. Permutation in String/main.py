from collections import defaultdict, Counter

class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        dict1 = Counter(s1)
        dict2 = defaultdict(int)

        for rt in range(len(s1), len(s2)+1, 1):
            dict2.clear()

            lt = rt -len(s1)
            for idx in range(lt, rt):
                dict2[s2[idx]] += 1

            if dict1 == dict2:
                return True

        return False
