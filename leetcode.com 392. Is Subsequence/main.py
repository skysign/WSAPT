from collections import deque

class Solution:
    def isSubsequence(self, s: str, t: str) -> bool:
        ss, tt = deque(list(s)), deque(list(t))

        while ss and tt:
            if ss[0] == tt.popleft():
                ss.popleft()

        return True if not ss else False
