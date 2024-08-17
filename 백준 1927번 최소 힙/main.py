import sys
from typing import List
import heapq


def solve():
    N: int = int(sys.stdin.readline().strip())
    heap: List[int] = []
    heapq.heapify(heap)

    for _ in range(N):
        n: int = int(sys.stdin.readline().strip())

        if n == 0:
            if len(heap) == 0:
                print(0)
            else:
                print(heapq.heappop(heap))
        else:
            heapq.heappush(heap, n)


if __name__ == '__main__':
    solve()
