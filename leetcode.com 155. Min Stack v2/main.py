class MinStack:

    def __init__(self):
        self.stk = []

    def push(self, val: int) -> None:
        val_min = self.getMin()

        if val_min is None:
            val_min = val
        elif val_min > val:
            val_min = val

        self.stk.append([val, val_min])

    def pop(self) -> None:
        self.stk.pop()

    def top(self) -> int:
        return self.stk[-1][0]

    def getMin(self) -> int:
        if self.stk:
            return self.stk[-1][1]

        return None
