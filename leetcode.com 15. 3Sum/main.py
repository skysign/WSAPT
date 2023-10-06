from typing import List

class Solution:
    def threeSum(self, dt: List[int]) -> List[List[int]]:
        myset = set()
        dt.sort()

        for plt in range(0, len(dt) -2):
            target = dt[plt] * -1
            prt = len(dt) - 1
            pmid = plt + 1

            while pmid < prt:
                if dt[pmid] + dt[prt] == target:
                    tmp = [dt[plt], dt[pmid], dt[prt]]
                    tmp.sort()
                    tmp2 = tuple(tmp)
                    myset.add(tmp2)
                    pmid +=1
                    prt -=1
                elif dt[pmid] + dt[prt] < target:
                    pmid +=1
                elif dt[pmid] + dt[prt] > target:
                    prt -=1

        rlt = []

        for item in myset:
            rlt.append(list(item))

        return rlt