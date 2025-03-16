class Solution:
    def addBinary(self, a: str, b: str) -> str:
        l_max = max(len(a), len(b))
        l = l_max - min(len(a), len(b))
        if len(a) < len(b):
            a = '0' * l + a
        if len(b) < len(a):
            b = '0' * l + b

        carry = 0
        answer = ['0' for _ in range(l_max)]

        for idx in range(-1, -l_max - 1, -1):
            v = int(a[idx]) + int(b[idx]) + carry
            if v == 3:
                carry = 1
                answer[idx] = str(1)[0]
            elif v == 2:
                carry = 1
                answer[idx] = str(0)[0]
            elif v == 1:
                carry = 0
                answer[idx] = str(1)[0]
            elif v == 0:
                carry = 0
                answer[idx] = str(0)[0]

        answer = ''.join(answer)

        if carry == 1:
            answer = '1' + answer

        return answer
