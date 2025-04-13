from typing import List


class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        dict = {}
        dict['2'] = ['a', 'b', 'c']
        dict['3'] = ['d', 'e', 'f']
        dict['4'] = ['g', 'h', 'i']
        dict['5'] = ['j', 'k', 'l']
        dict['6'] = ['m', 'n', 'o']
        dict['7'] = ['p', 'q', 'r', 's']
        dict['8'] = ['t', 'u', 'v']
        dict['9'] = ['w', 'x', 'y', 'z']

        queue = ['']
        queue2 = []

        for d in digits:
            for prev in queue:
                for new in dict[d]:
                    queue2.append(prev + new)

            queue = queue2
            queue2 = []

        return list(filter(lambda a: len(a) > 0, queue))
