import heapq

class MedianFinder:

    def __init__(self):
        self.left = []
        self.right = []

    def addNum(self, num: int) -> None:
        item = -1 * heapq.heappushpop(self.left, -num)
        item = heapq.heappushpop(self.right, item)

        if len(self.left) == len(self.right):
            heapq.heappush(self.left, -item)
        else:
            heapq.heappush(self.right, item)

    def findMedian(self) -> float:
        if (len(self.left) + len(self.right)) % 2 == 0:
            return ((-1 * self.left[0]) + self.right[0]) / 2
        else:
            return -1 * self.left[0]


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()