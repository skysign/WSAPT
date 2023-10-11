from collections import deque

# leetcode.com 125. Valid Palindrome
#
# 유튜브 문제 풀이: https://youtu.be/vfebH2GbEdA?si=UqUf0BohwrIPMLwI
#
# 파이썬 소스: https://bit.ly/46hux2j
#
# 문제 링크: https://bit.ly/3rMr6l8

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