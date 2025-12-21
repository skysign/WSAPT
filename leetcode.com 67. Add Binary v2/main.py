class Solution:
    def addBinary(self, a: str, b: str) -> str:
        mx_len = max(len(a), len(b))

        if len(a) < mx_len:
            a = '0' * (mx_len - len(a)) + a

        if len(b) < mx_len:
            b = '0' * (mx_len - len(b)) + b

        a, b, r = list(a), list(b), []
        a.reverse()
        b.reverse()
        carry = 0

        for i in range(mx_len):
            ai, bi = int(a[i]), int(b[i])
            carry, ri = divmod(ai + bi + carry, 2)
            r.append(str(ri))

        if carry:
            r.append(str(carry))

        r.reverse()

        return ''.join(r)