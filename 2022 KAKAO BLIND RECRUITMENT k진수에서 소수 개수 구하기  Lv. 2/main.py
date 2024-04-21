from math import sqrt


def solution(n, k):
    answer = 0
    vs = jinbum(n, k)

    for v in vs.split('0'):
        if v == '' or v == '1':
            continue

        if is_prime(int(v)):
            answer += 1

    return answer


def jinbum(n, k):
    if n == 0:
        return ''

    return jinbum(n // k, k) + str(n % k)


def is_prime(v: int) -> bool:
    for n in range(2, int(sqrt(v)) + 1):
        if 0 == v % n:
            return False

    return True
