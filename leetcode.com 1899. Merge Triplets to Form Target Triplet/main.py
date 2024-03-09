from typing import List

class Solution:
    def mergeTriplets(self, triplets: List[List[int]], target: List[int]) -> bool:
        dt0 = []
        dt1 = []
        dt2 = []

        for t in triplets:
            if not (t[0] > target[0] or t[1] > target[1] or t[2] > target[2]):
                dt0.append(t[0])
                dt1.append(t[1])
                dt2.append(t[2])

        return target[0] in dt0 and target[1] in dt1 and target[2] in dt2
