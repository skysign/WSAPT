class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1

        if n == 1:
            return x

        if n == -1:
            return 1 / x

        if n > 0:
            n1 = n // 2
            n3 = n - (n1 * 2)
            r1 = self.myPow(x, n1)
            return r1 * r1 * self.myPow(x, n3)

        nn = abs(n)
        n1 = nn // 2
        n3 = nn - (n1 * 2)
        r1 = self.myPow(x, n1)
        return 1 / (r1 * r1 * self.myPow(x, n3))
