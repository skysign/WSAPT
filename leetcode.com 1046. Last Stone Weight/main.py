from typing import List
from queue import PriorityQueue

class Solution:
    def __init__(self):
        self.max = 1000

    def lastStoneWeight(self, stones: List[int]) -> int:
        pq = PriorityQueue()

        for stone in stones:
            pq.put((self.max - stone, stone))

        while pq.qsize() > 1:
            y = pq.get()[1]
            x = pq.get()[1]

            if x == y:
                continue
            elif x != y:
                n = y - x
                pq.put((self.max - n, n))

        if pq.qsize() >= 1:
            return pq.get()[1]

        return 0