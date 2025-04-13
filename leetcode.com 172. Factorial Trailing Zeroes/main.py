class Solution:
    def trailingZeroes(self, n: int) -> int:
        if n == 0:
            return 0

        v = 1

        for a in range(2, n + 1):
            v *= a

        answer = 0

        q, r = divmod(v, 10)

        while r == 0:
            answer += 1
            v = q
            q, r = divmod(v, 10)

        return answer