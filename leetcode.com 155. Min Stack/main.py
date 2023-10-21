class MinStack:

    def __init__(self):
        self.stk = []
        self.min_stk = []

    def push(self, val: int) -> None:
        self.stk.append(val)

        if len(self.min_stk) > 0:
            self.min_stk.append(min(self.min_stk[-1], val))
        else:
            self.min_stk.append(val)

    def pop(self) -> None:
        self.min_stk.pop(len(self.stk) -1)
        return self.stk.pop(len(self.stk) -1)

    def top(self) -> int:
        return self.stk[-1]

    def getMin(self) -> int:
        return self.min_stk[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()