import sys

nth0 = [-1 for _ in range(41)]
nth0[0] = 1
nth0[1] = 0
nth1 = [-1 for _ in range(41)]
nth1[0] = 0
nth1[1] = 1
found = [False for _ in range(41)]
found[0] = True
found[1] = True

def solve():
    global nth0, nth1, found

    N = int(sys.stdin.readline().strip())
    inputs = []

    for _ in range(N):
        nth = int(sys.stdin.readline().strip())
        inputs.append(nth)

    mx = max(inputs)
    fibo(mx)

    for input in inputs:
        result = '%d %d' % (nth0[input], nth1[input])
        print(result)


def fibo(nth: int):
    global nth0, nth1, found

    if found[nth]:
        return nth0[nth], nth1[nth]
    else:
        a0, a1 = fibo(nth - 1)
        b0, b1 = fibo(nth - 2)
        found[nth] = True
        nth0[nth] = a0 + b0
        nth1[nth] = a1 + b1

        return nth0[nth], nth1[nth]


if __name__ == '__main__':
    solve()
