class Solution:
    def hammingWeight(self, n: int) -> int:
        answer = 0

        while n > 1:
            q, r = divmod(n, 2)
            if r == 1:
                answer += 1
            n = q

        if n == 1:
            answer += 1

        return answer
