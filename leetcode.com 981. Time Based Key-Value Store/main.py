from typing import Dict, List


class MyTimeMapValue:
    def __init__(self, value: str, timestamp: int):
        self.values: Dict = {timestamp: value}
        self.max_timestamp: int = timestamp

    def set(self, value: str, timestamp: int) -> None:
        self.values[timestamp] = value
        self.max_timestamp = max(self.max_timestamp, timestamp)

    def get(self, timestamp: int):
        if timestamp > self.max_timestamp:
            return self.values[self.max_timestamp]

        keys_times = list(self.values.keys())
        idx = self.binary_search(keys_times, timestamp, 0, len(keys_times) - 1)
        if idx == None:
            return ""
        else:
            mykey = keys_times[idx]
            rtn = self.values[mykey]
            return rtn

    def binary_search(self, dt: List[int], target: int, bgn, end):
        rtn = None

        while bgn <= end:
            mid = (bgn + end) //2

            if dt[mid] == target:
                return mid
            elif dt[mid] <= target:
                rtn = mid
                bgn = mid +1
            else:
                end = mid -1

        return rtn


class TimeMap:
    def __init__(self):
        self.dt: Dict = {}
        self.max_timestamps: Dict = {}

    def set(self, key: str, value: str, timestamp: int) -> None:
        if key not in self.dt:
            self.dt[key] = {}
            keyMapValue = MyTimeMapValue(value, timestamp)
            self.dt[key] = keyMapValue
        else:
            self.dt[key].set(value, timestamp)

    def get(self, key: str, timestamp: int) -> str:
        if key in self.dt:
            return self.dt[key].get(timestamp)

        return ""



# Your TimeMap object will be instantiated and called as such:
# obj = TimeMap()
# obj.set(key,value,timestamp)
# param_2 = obj.get(key,timestamp)