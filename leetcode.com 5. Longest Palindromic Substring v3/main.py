class Solution:
    def longestPalindrome(self, s: str) -> str:
        min_left, max_right = 0, 0
        max_length = 0

        for i in range(len(s)):
            right = left = i

            while 0 <= left and right < len(s):
                if s[left] == s[right]:
                    left, right = left - 1, right + 1
                else:
                    break

            if max_length < (right - 1) - (left + 1) + 1:
                max_length = (right - 1) - (left + 1) + 1
                min_left, max_right = (left + 1), (right - 1)

            left = i
            right = left + 1

            while 0 <= left and right < len(s):
                if s[left] == s[right]:
                    left, right = left - 1, right + 1
                else:
                    break

            if max_length < (right - 1) - (left + 1) + 1:
                max_length = (right - 1) - (left + 1) + 1
                min_left, max_right = (left + 1), (right - 1)

        return s[min_left:max_right + 1]
