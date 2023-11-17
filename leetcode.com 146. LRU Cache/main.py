from collections import OrderedDict


class LRUCache:

    def __init__(self, capacity: int):
        self.dict = OrderedDict()
        self.capacity = capacity

    def get(self, key: int) -> int:
        if key not in self.dict.keys():
            return -1
        else:
            return self.dict.setdefault(key, self.dict.pop(key))

    def put(self, key: int, value: int) -> None:
        try:
            self.dict.move_to_end(key)
            self.dict[key] = value
        except KeyError:
            self.dict[key] = value

            if self.capacity < len(self.dict):
                self.dict.popitem(last=False)

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)