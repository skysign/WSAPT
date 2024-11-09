import heapq
import sys


def solve():
    input = sys.stdin.readline
    N, K = map(int, input().strip().split())
    jewels_weight_value = []
    bags = []

    for _ in range(N):
        heapq.heappush(jewels_weight_value, list(map(int, input().strip().split())))

    for _ in range(K):
        bags.append(int(input().strip()))

    # 가방을 적은 무게를 담을 수 있는 가방부터, 큰 무게를 담을 수 있는 가방으로 정렬
    # 가방이 정렬되었으므로,
    # i번째 가방에 넣을 수 있는 보석은, i+1번째 가방에도 넣을 수 있다.
    bags.sort()

    jewels_value = []
    answer = 0

    for bag_weight in bags:
        # 이 가방에 넣을 수 있는 보석을 jewel_value에 넣음
        while jewels_weight_value and bag_weight >= jewels_weight_value[0][0]:
            heapq.heappush(jewels_value, -heapq.heappop(jewels_weight_value)[1])

        if jewels_value:
            # jewels_value 에는 i 번째 가방에 넣을 수 있는
            # 최대가치의 보석을 넣는다.
            answer += -heapq.heappop(jewels_value)
            # 이 때, jewels_value에 1개 이상의 보석가차가 남아 있을 수 있다.
            # 하지만, i 번째 가방에 넣을 수 있는 무게는, i +1 번째 가방에 넣을 수 있는 무게 보다, 작거나 같다.
            # 따라서, 다음번 가방에 넣을 수 있다.

    print(answer)


if __name__ == '__main__':
    solve()
