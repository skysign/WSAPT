import sys

# Two-pointer로 풀이
def solve():
    N = int(sys.stdin.readline().strip())
    dt = list(map(int, sys.stdin.readline().strip().split()))
    t1, t2, sum_0 = 0, 0, 2000000000 + 1
    idx_left, idx_right = 0, len(dt) - 1

    while idx_left < idx_right:
        t1_local, t2_local = dt[idx_left], dt[idx_right]
        sum_local = t1_local + t2_local

        if abs(sum_local) <= abs(sum_0):
            t1, t2, sum_0 = t1_local, t2_local, sum_local

        if sum_local < 0:
            idx_left += 1
        elif sum_local > 0:
            idx_right -= 1
        else:
            break

    print(t1, t2)

# Binary search로 풀이
# def solve():
#     N = int(sys.stdin.readline().strip())
#     dt = list(map(int, sys.stdin.readline().strip().split()))
#
#     t1, t2, sum_0 = 0, 0, 2000000000 + 1
#
#     for idx in range(len(dt)):
#         t1_local = dt[idx]
#         idx_left, idx_right = idx + 1, len(dt) - 1
#
#         while idx_left <= idx_right:
#             idx_mid = (idx_left + idx_right) // 2
#             t2_local = dt[idx_mid]
#             sum_local = t1_local + t2_local
#
#             if abs(sum_0) > abs(sum_local):
#                 t1, t2, sum_0 = t1_local, t2_local, sum_local
#
#             if sum_local > 0:
#                 idx_right = idx_mid - 1
#             elif sum_local < 0:
#                 idx_left = idx_mid + 1
#             else:
#                 break
#
#     print(str(t1) + ' ' + str(t2))

if __name__ == '__main__':
    solve()
