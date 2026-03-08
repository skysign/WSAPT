from typing import List
from collections import deque


class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        q1, q2 = deque(nums1[0:m]), deque(nums2[0:n])
        answer = []

        while q1 and q2:
            if q1[0] < q2[0]:
                answer += [q1.popleft()]
            else:
                answer += [q2.popleft()]

        while q1:
            answer += [q1.popleft()]

        while q2:
            answer += [q2.popleft()]

        for idx in range(m + n):
            nums1[idx] = answer[idx]

