class Solution:
    def isValid(self, s: str) -> bool:
        dt = []

        for c in s:
            if len(dt) == 0:
                dt.append(c)
                continue

            if dt[-1] == '(' and c == ')':
                dt.pop()
            elif dt[-1] == '[' and c == ']':
                dt.pop()
            elif dt[-1] == '{' and c == '}':
                dt.pop()
            else:
                dt.append(c)

        return True if len(dt) == 0 else False
