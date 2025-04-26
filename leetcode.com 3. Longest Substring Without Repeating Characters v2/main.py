from typing import Deque
from collections import deque


class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        mx = 0
        substring = deque()

        for c in s:
            if substring.__contains__(c):
                while substring.__contains__(c):
                    substring.popleft()

            substring.append(c)
            mx = max(mx, len(substring))

        return mx