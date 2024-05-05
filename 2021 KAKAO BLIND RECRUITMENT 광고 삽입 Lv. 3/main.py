from typing import List


def solution(play_time: str, adv_time: str, logs: List[str]):
    play_time = hms(play_time)
    adv_time = hms(adv_time)
    dt = [0 for _ in range(play_time + 1)]
    for log in logs:
        fr, to = list(map(hms, log.split('-')))
        dt[fr] += 1
        dt[to] -= 1

    for i in range(play_time):
        dt[i + 1] += dt[i]

    for i in range(play_time):
        dt[i + 1] += dt[i]

    mx = 0
    mn_bgn = 0

    for bgn in range(play_time - adv_time + 1):
        end = bgn + adv_time
        sum = dt[end - 1]

        if bgn > 0:
            sum -= dt[bgn - 1]

        if mx < sum:
            mx = sum
            mn_bgn = bgn

    answer = hms2(mn_bgn)

    return answer


def hms(hms: str):
    h, m, s = list(map(int, hms.split(':')))
    return h * 60 * 60 + m * 60 + s


def hms2(n: int):
    h = '%02d' % (n // 3600)
    m = '%02d' % ((n % 3600) // 60)
    s = '%02d' % ((n % 3600) % 60)
    return h + ':' + m + ':' + s
