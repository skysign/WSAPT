import copy
from typing import List

class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if len(digits) <= 0:
            return []

        dt = {}
        dt[2] = [['a'], ['b'], ['c']]
        dt[3] = [['d'], ['e'], ['f']]
        dt[4] = [['g'], ['h'], ['i']]
        dt[5] = [['j'], ['k'], ['l']]
        dt[6] = [['m'], ['n'], ['o']]
        dt[7] = [['p'], ['q'], ['r'], ['s']]
        dt[8] = [['t'], ['u'], ['v']]
        dt[9] = [['w'], ['x'], ['y'], ['z']]

        inputs = []

        for ch in digits:
            inputs.append(int(ch))

        output = []
        number = inputs.pop(0)
        output.extend(copy.deepcopy(dt[number]))

        output = self.rec(dt, inputs, output)

        answer = []

        for item in output:
            tmp_str = ''
            for ch in item:
                tmp_str += ch

            answer.append(tmp_str)

        return answer

    def rec(self, dt, inputs: List[int], output):
        if len(inputs) <= 0:
            return output

        number = inputs.pop(0)
        tmp = []

        for item in output:
            for ch in dt[number]:
                tmp_item = copy.deepcopy(item)
                tmp_item.append(ch[0])
                tmp.append(tmp_item)

        output = tmp

        return self.rec(dt, inputs, output)
