import random


class RandomizedSet:
    def __init__(self):
        self.dt = {}

    def insert(self, val: int) -> bool:
        if val not in self.dt:
            self.dt[val] = None
            return True

        return False

    def remove(self, val: int) -> bool:
        if val in self.dt:
            del self.dt[val]
            return True

        return False

    def getRandom(self) -> int:
        return random.choice(list(self.dt.keys()))

# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
