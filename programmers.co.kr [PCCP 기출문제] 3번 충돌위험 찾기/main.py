import queue
from collections import deque, defaultdict


def solution(points, routes):
    answer = 0
    dt = defaultdict(int)

    for route in routes:
        depth = 0
        for i in range(1, len(route)):
            r, c, dst_r, dst_c = points[route[i-1]-1][0], points[route[i-1]-1][1], points[route[i]-1][0], points[route[i]-1][1]
            if i == 1:
                dt[(depth, r, c)] += 1
                depth += 1

            while r != dst_r or c != dst_c:
                if r > dst_r:
                    r -= 1
                elif r < dst_r:
                    r += 1
                elif c > dst_c:
                    c -= 1
                elif c < dst_c:
                    c += 1

                dt[(depth, r, c)] += 1
                depth += 1

    for key in dt:
        if dt[key] > 1:
            answer += 1

    return answer