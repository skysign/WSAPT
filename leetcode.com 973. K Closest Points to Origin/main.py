from typing import List
import heapq

class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        heap = []

        for x, y in points:
            distance = x*x + y*y

            if len(heap) == k:
                heapq.heappushpop(heap, (-distance, x, y))
            else:
                heapq.heappush(heap, (-distance, x, y))

        return [[x, y] for dist, x, y in heap]