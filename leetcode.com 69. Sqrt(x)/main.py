class Solution:
    def mySqrt(self, x: int) -> int:
        left, right = 1, x

        while left <= right:
            mid = (left + right) // 2
            v = x // mid
            if mid == v:
                return mid

            if mid < v:
                left = mid + 1
            else: # mid > v
                right = mid -1

        return right