class Solution:
    def reverseWords(self, s: str) -> str:
        s = s.strip()
        ss = s.split()
        ss.reverse()
        return ' '.join(ss)