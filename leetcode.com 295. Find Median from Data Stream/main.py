class MedianFinder:

    def __init__(self):
        self.dt = []

    def addNum(self, num: int) -> None:
        for idx in range(len(self.dt)):
            if num <= self.dt[idx]:
                self.dt.insert(idx, num)
                return

        self.dt.append(num)

    def findMedian(self) -> float:
        if len(self.dt) % 2 == 0:
            idx = int(len(self.dt) / 2)
            return (self.dt[idx-1] + self.dt[idx]) / 2
        else:
            idx = int(len(self.dt) / 2)
            return self.dt[idx]

# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()