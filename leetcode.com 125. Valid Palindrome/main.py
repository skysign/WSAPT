from collections import deque

class Solution:
    def isPalindrome(self, s: str) -> bool:
        queue = deque()

        for c in s:
            if c.isalnum():
                queue.append(c.lower())

        while len(queue) > 1:
            l = queue.popleft()
            r = queue.pop()
            if l != r:
                return False

        return True