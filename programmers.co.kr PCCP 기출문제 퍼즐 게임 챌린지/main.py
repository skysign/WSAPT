def solution(diffs, times, limit):
    answer = 0

    left, right = 1, 300000
    while left <= right:
        mid = (left + right) // 2
        if solve(diffs, times, limit, mid):
            right = mid -1
            answer = mid
        else:
            left = mid + 1

    return answer


def solve(diffs, times, limit, level):
    consumed_time = times[0]

    for idx in range(1, len(diffs)):
        if diffs[idx] <= level:
            consumed_time += times[idx]
        else:
            consumed_time += (times[idx - 1] + times[idx]) * (diffs[idx] - level) + times[idx]

        if consumed_time > limit:
            return False

    return True
