class Solution:
    def isPalindrome(self, x: int) -> bool:
        s = str(x)
        idxl, idxr = 0, len(s) - 1

        while idxl <= idxr:
            if s[idxl] != s[idxr]:
                return False

            idxl += 1
            idxr -= 1

        return True
