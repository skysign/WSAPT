from typing import List

class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if len(digits) == 0:
            return []

        dt = {}
        dt[2] = ['a', 'b', 'c']
        dt[3] = ['d', 'e', 'f']
        dt[4] = ['g', 'h', 'i']
        dt[5] = ['j', 'k', 'l']
        dt[6] = ['m', 'n', 'o']
        dt[7] = ['p', 'q', 'r', 's']
        dt[8] = ['t', 'u', 'v']
        dt[9] = ['w', 'x', 'y', 'z']

        output = dt[int(digits[:1])]

        for c in digits[1:]:
            idx = int(c)
            output2 = []

            for pre in output:
                for post in dt[idx]:
                    output2.append(pre + post)

            output = output2

        return output

