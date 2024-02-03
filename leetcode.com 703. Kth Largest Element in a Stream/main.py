from typing import List

class KthLargest:
    def __init__(self, k: int, nums: List[int]):
        self.k = k
        self.nums = sorted(nums)

    def add(self, val: int) -> int:
        if len(self.nums) == 0:
            self.nums.append(val)
        elif len(self.nums) > 0   \
            and self.nums[len(self.nums) -1] <= val:
            self.nums.append(val)
        else:
            for idx in range(len(self.nums)):
                if val < self.nums[idx]:
                    self.nums.insert(idx, val)
                    break

        return self.nums[len(self.nums) - self.k]

# Your KthLargest object will be instantiated and called as such:
# obj = KthLargest(k, nums)
# param_1 = obj.add(val)