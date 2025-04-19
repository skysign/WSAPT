class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        left, right = list(ransomNote), list(magazine)

        for c in left:
            try:
                idx = right.index(c)
                right.pop(idx)
            except ValueError:
                return False

        return True
