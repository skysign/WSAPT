from typing import List

class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        if sum(gas) < sum(cost):
            return -1

        start = 0
        fuel = 0

        for idx in range(len(gas)):
            fuel += (gas[idx] - cost[idx])

            if fuel < 0:
                fuel = 0
                start = idx +1

        return start
