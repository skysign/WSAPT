# 세그먼트 트리에 대해서 잘 설명해주고 있는 영상
# https://www.youtube.com/watch?v=1d9sqmuLy-o
from collections import defaultdict
from typing import Any, List

H_MAX = 1000000


class SegmentTree:
    def __init__(self):
        self.base = 1
        while self.base < H_MAX:
            self.base *= 2

        self.nodes = [(0, 0) for _ in range(self.base * 2)]

    def get_max(self, idx_left: int, idx_right: int) -> tuple[int, int] | Any:
        idx_left += self.base
        idx_right += self.base

        answer = (0, 0)

        while idx_left < idx_right:
            if idx_left & 1:
                answer = max(answer, self.nodes[idx_left])
                idx_left = (idx_left + 1) >> 1
            else:
                idx_left >>= 1

            if idx_right & 1:
                idx_right >>= 1
            else:
                answer = max(answer, self.nodes[idx_right])
                idx_right = (idx_right - 1) >> 1

        if idx_left == idx_right:
            answer = max(answer, self.nodes[idx_left])

        return answer

    def update(self, h, value):
        idx_h = h + self.base
        self.nodes[idx_h] = (value, h)

        idx_parent = idx_h >> 1

        while idx_parent > 0:
            self.nodes[idx_parent] = max(self.nodes[idx_parent * 2], self.nodes[idx_parent * 2 + 1])
            idx_parent >>= 1


def add_mountain(segment: SegmentTree, h_dp, hs_dp, h, hs):
    hs.append(h)
    dp, _ = segment.get_max(1, h - 1)
    h_dp.append(dp +1)
    hs_dp[h].append(dp + 1)
    segment.update(h, dp + 1)


def remove_mountain(segment: SegmentTree, h_dp, hs_dp, hs: List):
    h_remove = hs.pop()
    h_dp.pop()
    hs_dp[h_remove].pop()
    dp = 0
    if len(hs_dp[h_remove]) > 0:
        dp = hs_dp[h_remove][-1]

    segment.update(h_remove, dp)


def solve():
    segment = SegmentTree()
    hs = []
    h_dp = [0]
    hs_dp = [[] for _ in range(H_MAX)]

    Q = int(input())
    Q -= 1
    _, n, *mountains = list(map(int, input().split()))

    for h in mountains:
        add_mountain(segment, h_dp, hs_dp, h, hs)

    for _ in range(Q):
        cmd, *vs = list(map(int, input().split()))

        if cmd == 200:
            h = vs[0]
            add_mountain(segment, h_dp, hs_dp, h, hs)
        elif cmd == 300:
            remove_mountain(segment, h_dp, hs_dp, hs)
        elif cmd == 400:
            h_cable_car = vs[0]
            dp1 = h_dp[h_cable_car]
            dp2, h = segment.get_max(1, H_MAX - 1)
            answer = (dp1 + dp2 -1) * H_MAX + h
            print(answer)


if __name__ == '__main__':
    solve()
