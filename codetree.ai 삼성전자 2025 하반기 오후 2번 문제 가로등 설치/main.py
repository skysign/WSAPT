import heapq
from typing import Optional


def solve():
    cnt_cmds = int(input().strip())
    N, M = 0, 0
    streetlights_Ms = [-1]
    # 가장 왼쪽에 있는 가로등의 m 값을 구하기 위한 heap
    streetlights_left_heap = []
    # 가장 오른쪽에 있는 가로등의 m 값을 구하기 위한 heap
    streetlights_right_heap = []
    # 거리를 저장하고 있는 heap
    streetlights_distance_heap = []

    # 두개의 가로등 사이의 거리를 정의한 클래스
    class Distance:
        def __init__(self, m_left, m_right, distance, m_left_position):
            self.m_left = m_left  # 거리의 왼쪽 가로등 m 값
            self.m_right = m_right  # 거리의 오른쪽 가로등 m 값
            self.distance = distance  # self.m_right 가로등의 위치값 - self.m_left 가로등의 위치값
            self.m_left_position = m_left_position  # self.m_left 가로등의 위치값

        def __lt__(self, other):
            # 두 가로등의 사이의 거리가 같다면
            if self.distance == other.distance:
                # 왼쪽에 있는 가로등의 위치값이 작은 것을 선택한다.
                return self.m_left_position < other.m_left_position
            # 두 가로등의 사이의 거리가 짧은것을 선택
            return self.distance > other.distance

    def get_max_distance():
        max_distance: Optional[Distance] = None

        while streetlights_distance_heap:
            distance = streetlights_distance_heap[0]

            if streetlights_Ms[distance.m_left] == distance.m_left_position \
                    and streetlights_Ms[distance.m_right] == distance.distance + distance.m_left_position:
                max_distance = distance
                break

            heapq.heappop(streetlights_distance_heap)

        return max_distance

    for _ in range(cnt_cmds):
        tmps = list(map(int, input().strip().split(' ')))
        cmd = tmps[0]

        if cmd == 100:
            N, M = tmps[1], tmps[2]
            # m은 1 <= m <= M
            # m번째 가로등의 위치
            streetlights_Ms = [-1] + list(map(int, tmps[3:]))

            # streetlights_M_lefts[m]은 m번째 가로등의 왼쪽 가로등의 m값
            # 0번째 가로등은 존재하지 않으므로, [-1]
            # 1번째 가로등의 왼쪽 가로등은 없으므로, [-1]
            streetlights_M_lefts = [-1] + [-1] + [idx for idx in range(1, M)]

            # streetlights_M_rights[m]은 m번째 가로등의 오른쪽 가로등의 m값
            # 0번째 가로등은 존재하지 않으므로, [-1]
            # M번째 가로등의 오른쪽 가로등은 없으므로, [-1]
            streetlights_M_rights = [-1] + [idx + 1 for idx in range(1, M)] + [-1]

            for m in range(1, len(streetlights_Ms)):
                # position은 가로등의 위치
                position = streetlights_Ms[m]
                heapq.heappush(streetlights_left_heap, [position, m])
                heapq.heappush(streetlights_right_heap, [-position, m])

                if 1 < m:
                    left_position = streetlights_Ms[m - 1]
                    heapq.heappush(streetlights_distance_heap,
                                   Distance(m - 1, m, position - left_position, left_position))

        elif cmd == 200:
            max_distance = get_max_distance()
            heapq.heappop(streetlights_distance_heap)
            new_position = (max_distance.distance + 1) // 2 + max_distance.m_left_position
            new_m = len(streetlights_Ms)
            streetlights_Ms.append(new_position)

            streetlights_M_rights[max_distance.m_left] = new_m
            streetlights_M_lefts[max_distance.m_right] = new_m
            streetlights_M_rights.append(max_distance.m_right)
            streetlights_M_lefts.append(max_distance.m_left)

            heapq.heappush(streetlights_left_heap, [new_position, new_m])
            heapq.heappush(streetlights_right_heap, [-new_position, new_m])

            heapq.heappush(streetlights_distance_heap, \
                           Distance(max_distance.m_left, new_m, new_position - max_distance.m_left_position, max_distance.m_left_position))
            heapq.heappush(streetlights_distance_heap, \
                           Distance(new_m, max_distance.m_right, max_distance.distance - (new_position - max_distance.m_left_position), new_position))

        elif cmd == 300:
            D = tmps[1]
            streetlights_Ms[D] = -1

            left_m = streetlights_M_lefts[D]
            right_m = streetlights_M_rights[D]

            if left_m != -1:
                streetlights_M_rights[left_m] = right_m
            if right_m != -1:
                streetlights_M_lefts[right_m] = left_m

            if left_m != -1 and right_m != -1:
                heapq.heappush(streetlights_distance_heap, \
                               Distance(left_m, right_m, \
                                        streetlights_Ms[right_m] - streetlights_Ms[left_m],
                                        streetlights_Ms[left_m]))

        elif cmd == 400:
            # 가장 왼쪽에 있는 가로등의 위치, 가장 오른쪽에 있는 가로등의 위치
            min_position, max_position = 0, N + 1

            while streetlights_left_heap:
                position, m = streetlights_left_heap[0]

                if streetlights_Ms[m] == position:
                    min_position = position
                    break

                heapq.heappop(streetlights_left_heap)

            while streetlights_right_heap:
                position, m = streetlights_right_heap[0]

                if streetlights_Ms[m] == -position:
                    max_position = -position
                    break

                heapq.heappop(streetlights_right_heap)

            max_distance = get_max_distance()

            mx_r = max((min_position - 1) * 2, (N - max_position) * 2, max_distance.distance)

            print(mx_r)


if __name__ == '__main__':
    solve()
