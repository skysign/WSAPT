class Solution:
    def isPalindrome(self, s: str) -> bool:
        dt = ''

        for c in s:
            if c.isalnum():
                dt += c.lower()

        return self.is_palindrome(dt)

    def is_palindrome(self, dt):
        if dt == '':
            return True

        idx_left, idx_right = 0, len(dt) - 1

        while idx_left <= idx_right:
            if dt[idx_left] != dt[idx_right]:
                return False

            idx_left += 1
            idx_right -= 1

        return True
