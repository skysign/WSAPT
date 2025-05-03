class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.dict = {}
        self.keys = []

    def get(self, key: int) -> int:
        if key in self.keys:
            self.keys.remove(key)
            self.keys = [key] + self.keys
            return self.dict[key]

        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.keys:
            self.keys.remove(key)
            self.keys = [key] + self.keys
            self.dict[key] = value
            return

        self.keys = [key] + self.keys
        self.dict[key] = value

        if len(self.keys) > self.capacity:
            del self.dict[self.keys[-1]]
            self.keys = self.keys[:-1]

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)