import sys
from typing import List, Deque
from collections import deque


def solve():
    dts = [c for c in sys.stdin.readline().strip()]
    queue = deque(dts)

    def in2post(queue: Deque) -> List[str]:
        result = []
        stk = []

        ops_plus_minus = ['+', '-']
        ops_multi_divide = ['*', '/']

        while queue:
            c = queue.popleft()

            if c.isalpha():
                result.append(c)
            else:
                if c == '(':
                    stk.append(c)
                elif c in ops_multi_divide:
                    while stk and (stk[-1] in ops_multi_divide):
                        result.append(stk.pop())

                    stk.append(c)
                elif c in ops_plus_minus:
                    while stk and stk[-1] != '(':
                        result.append(stk.pop())
                    stk.append(c)
                elif c == ')':
                    while stk and stk[-1] != '(':
                        result.append(stk.pop())

                    stk.pop()  # ( 제거

        while stk:
            result.append(stk.pop())

        return result

    rlt = in2post(queue)

    print(''.join(rlt))


if __name__ == '__main__':
    solve()
