import sys
from typing import List
import heapq


class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        heap = []
        answer = sys.maxsize

        for n in nums:
            heapq.heappush(heap, -n)

        for _ in range(k):
            answer = heapq.heappop(heap)

        return -answer
