from typing import List
import heapq
from collections import Counter

class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        if n == 0:
            return len(tasks)

        counts = Counter(tasks)

        tasks = list(counts.values())
        tasks = list(map(lambda x: -x, tasks))
        tasks.sort()

        heapq.heapify(tasks)
        queue = []
        time = 0

        while len(tasks) > 0 or len(queue) > 0:
            time += 1

            if len(tasks) > 0:
                task = heapq.heappop(tasks)
                task += 1

                if task != 0:
                    queue.append([task, time + n])

            if len(queue) > 0:
                if queue[0][1] == time:
                    [task, _] = queue.pop(0)
                    heapq.heappush(tasks, task)

        return time
