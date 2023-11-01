class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if len(s) == 1:
            return 1

        lt = 0
        rt = 1

        max_length = 0

        while lt < rt and rt <= len(s):
            max_length = max(max_length, rt - lt)

            if rt == len(s):
                break

            group = s[lt:rt]

            if s[rt] in group:
                lt += 1
                if lt == rt:
                    rt += 1
            else:
                rt += 1

        return max_length
