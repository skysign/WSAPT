from heapq import heappush, heappop
from typing import List


class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        heap, answer = [], []

        idx = 0
        window_lt = idx
        window_rt = min(idx + k - 1, len(nums) - 1)

        for idx in range(window_lt, window_rt+1):
            heappush(heap, (-nums[idx], idx))

        answer.append(-heap[0][0])

        for i in range(1, len(nums) -k +1, 1):
            idx = i + k -1
            window_lt = i
            window_rt = min(i + k - 1, len(nums) - 1)

            heappush(heap, (-nums[idx], idx))

            while not (window_lt <= heap[0][1] <= window_rt):
                heappop(heap)

            answer.append(-heap[0][0])

        return answer