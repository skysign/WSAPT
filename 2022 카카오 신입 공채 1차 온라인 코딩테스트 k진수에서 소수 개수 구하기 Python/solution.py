import string


def findN2(max_number):
    n = 1

    while n * n <= max_number:
        n += 1

    return n


def isPrimeNumber(number):
    if number <= 1:
        return False

    n2 = findN2(number)

    for n in range(2, n2 + 1):
        if number % n == 0 and n != number:
            return False  # number가 n 의 배수 임으로 not prime number

    return True


hexcode = string.digits + string.ascii_lowercase


def convert_jinbum_10_to_base(number10, base):
    q, r = divmod(number10, base)

    if q == 0:
        return hexcode[r]

    return convert_jinbum_10_to_base(q, base) + hexcode[r]


def solution(n, k):
    answer = 0
    number = convert_jinbum_10_to_base(n, k)
    number = str(number)

    for n in number.split('0'):
        if len(n) <= 0:
            continue

        if isPrimeNumber(int(n)):
            answer += 1

    return answer