# 2022 카카오 신입 공채 1차 온라인 코딩테스트 k진수에서 소수 개수 구하기 Python
#
# 유튜브 문제 풀이: https://youtu.be/Vs6rVanIxKk
#
# 파이썬소스: http://bit.ly/3JI1Frq
#
# 문제: https://bit.ly/3jsiNa9

import string

# x 약수 <= y
# y * y <= x

def findN2(number):
    n = 1

    while n * n <= number:
        n += 1

    return n

def IsPrimeNumber(number):
    if number <= 1:
        return False

    n2 = findN2(number)

    for n in range(2, n2):
        if number % n == 0 and n != number:
            return False

    return True;

# 0~~~~9 a~~z
hexcode = string.digits + string.ascii_lowercase

def convert_10_to_k(n, k):
    # n / k = q, r
    # 5 / 2 = 2 .. 1
    # 2 / 2 = 1 .. 0
    # 1 / 2 = 0 .. 1
    # 101 2진법 = 5 10진법
    q, r = divmod(n, k)

    if q == 0:
        return hexcode[r]

    return convert_10_to_k(q, k) + hexcode[r]

def solution(n, k):
    answer = 0
    # 10진법의 수가 -> k 진법으로 변환
    number = convert_10_to_k(n, k)

    # 123412350000000023512351235
    for n in number.split('0'):
        if len(n) <= 0:
            continue

        if IsPrimeNumber(int(n)):
            answer += 1

    return answer