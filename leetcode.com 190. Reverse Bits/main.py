class Solution:
    # n 값이 일반적인 int 값(123 형태)으로 주어짐
    # Example에서 n 이 바이너리로 주어지는 것 처럼 보여서 헷갈릴 수 있음
    def reverseBits(self, n: int) -> int:
        s = []
        while n > 0:
            n, r, = divmod(n, 2)
            s = [r] + s

        leading_zero = [0 for _ in range(32 - len(s))]
        s = leading_zero + s

        answer = 0

        v = 1
        answer += (v * s[0])

        for idx in range(1, len(s)):
            nn = int(s[idx])
            v *= 2
            answer += (v * nn)

        return answer
