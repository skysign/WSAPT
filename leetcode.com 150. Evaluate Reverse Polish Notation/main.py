from typing import List
from collections import deque

class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        queue = deque()
        operators = ['+', '-', '*', '/']

        for token in tokens:
            if token in operators:
                operator = token
                operand2 = queue.pop()
                operand1 = queue.pop()
                v = self.operate(operator, operand1, operand2)
                queue.append(v)
            else:
                queue.append(int(token))

        return queue.pop()


    def operate(self, operator, operand1: int, operand2: int):
        if operator == '+':
            return operand1 + operand2
        elif operator == '-':
            return operand1 - operand2
        elif operator == '*':
            return operand1 * operand2

        return int(operand1 / operand2)