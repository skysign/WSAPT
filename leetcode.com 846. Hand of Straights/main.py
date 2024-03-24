from collections import Counter
from typing import List
import heapq

class Solution:
    def isNStraightHand(self, hand: List[int], groupSize: int) -> bool:
        if 0 != len(hand) % groupSize:
            return False

        hands = Counter(hand)
        heap, removes = [], []

        for item in hands.items():
            heapq.heappush(heap, item)

        while heap:
            t, length = heapq.heappop(heap)
            removes.append((t, length-1))

            for i in range(1, groupSize):
                if len(heap) <= 0:
                    return False

                t1, length = heapq.heappop(heap)
                if t + 1 == t1:
                    t = t1
                    removes.append((t1, length - 1))
                else:
                    return False

            for item in removes:
                if item[1] > 0:
                    heapq.heappush(heap, item)

            removes.clear()

        return True