from typing import List, Deque
from collections import deque

MAX_NUMBER = 10 **6 +1

# leetcode.com 4. Median of Two Sorted Arrays
#
# 유튜브 문제 풀이: https://youtu.be/Ye_639SfzJE?si=Un9JlPPcfFAci0-W
#
# 파이썬 소스: https://bit.ly/3RPxmDv
#
# 문제 링크: https://bit.ly/3torpmN

class Solution:
    def get_next_min_number(self, q1: Deque[int], q2: Deque[int]):
        item1 = MAX_NUMBER
        item2 = MAX_NUMBER

        if len(q1) > 0:
            item1 = q1.popleft()

        if len(q2) > 0:
            item2 = q2.popleft()

        min_item = min(item1, item2)

        if item1 == item2:
            q2.insert(0, item2)
        elif item1 == min_item and item2 != MAX_NUMBER:
            q2.insert(0, item2)
        elif item2 == min_item and item1 != MAX_NUMBER:
            q1.insert(0, item1)

        return min_item

    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        total_length = len(nums1) + len(nums2)

        if total_length == 1:
            nums1.extend(nums2)
            return nums1[0]

        if total_length == 2:
            nums1.extend(nums2)
            return (nums1[0] + nums1[1]) / 2

        # 0 이면 짝수(even), 1이면 홀수(odd)
        evenOrOdd = total_length % 2

        length_to_skip = int(total_length / 2) if evenOrOdd == 1 else int((total_length - 2) / 2)

        q1 = deque(nums1)
        q2 = deque(nums2)

        for _ in range(length_to_skip):
            self.get_next_min_number(q1, q2)

        if evenOrOdd == 1:
            return self.get_next_min_number(q1, q2)

        number1 = self.get_next_min_number(q1, q2)
        number2 = self.get_next_min_number(q1, q2)

        return (number1 + number2) / 2
