class Solution:
    def isValid(self, s: str) -> bool:
        stk = []

        for c in s:
            if c == '(' or c == '[' or or c == '{':
                stk.append(c)
            elif len(stk) > 0 and \
                (stk[-1] == '(' and c == ')'      \
                    or stk[-1] == '[' and c == ']'  \
                    or stk[-1] == '{' and c == '}'):
                    stk.pop(len(stk) -1)
            else:
                stk.append(c)

        return len(stk) == 0