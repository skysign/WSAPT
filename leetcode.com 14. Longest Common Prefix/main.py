from typing import List


class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if len(strs) == 0:
            return ''

        if len(strs) == 1:
            return strs[0]

        answer = ''
        idx = 0

        for c in strs[0]:
            for s in strs[1:]:
                if len(s) > idx:
                    if c != s[idx]:
                        return answer
                else:
                    return answer

            idx += 1
            answer += c

        return answer