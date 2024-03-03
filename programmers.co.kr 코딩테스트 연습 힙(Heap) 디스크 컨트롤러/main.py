from typing import List
import heapq

def solution(jobs: List):
    current_time = 0
    answer = 0
    count = len(jobs)
    jobs.sort()
    heap = []

    while len(jobs) > 0 or len(heap) > 0:
        is_idle: bool = True

        while len(jobs) > 0 and jobs[0][0] <= current_time:
            [start_time, duration] = jobs.pop(0)
            heapq.heappush(heap, [duration, start_time])

        if len(heap) > 0:
            is_idle = False
            [duration, start_time] = heapq.heappop(heap)

            answer += (current_time - start_time)
            answer += duration
            current_time += duration

        if is_idle:
            current_time += 1

    return int(answer / count)
