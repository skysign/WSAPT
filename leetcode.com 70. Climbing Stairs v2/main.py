class Solution:
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n

        prev2 = 1
        prev1 = 2
        crnt = 0

        for idx in range(3, n + 1):
            crnt = prev1 + prev2
            prev2 = prev1
            prev1 = crnt

        return crnt
