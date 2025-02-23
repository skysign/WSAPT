import sys
import heapq


def solve():
    input = sys.stdin.readline
    N, K = map(int, input().strip().split())
    MVs = []
    for _ in range(N):
        Mi, Vi = map(int, input().strip().split())
        heapq.heappush(MVs, [Mi, Vi])

    bags = [int(input().strip()) for _ in range(K)]
    bags.sort()

    answer = 0
    tmp = []

    for bag in bags:
        while MVs:
            Mmin, Vmax = heapq.heappop(MVs)

            if bag >= Mmin:
                heapq.heappush(tmp, -Vmax)
            else:
                heapq.heappush(MVs, [Mmin, Vmax])
                break

        if tmp:
            Vminus = heapq.heappop(tmp)
            answer += -Vminus

    print(answer)


if __name__ == '__main__':
    solve()
