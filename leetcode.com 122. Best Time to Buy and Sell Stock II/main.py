from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        buy = prices[0]
        answer = 0

        for price in prices[1:]:
            if buy >= price:
                buy = price
            else:
                answer += (price - buy)
                buy = price

        return answer