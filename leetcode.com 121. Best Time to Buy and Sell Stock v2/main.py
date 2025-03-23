from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        answer = 0
        buy = prices[0]

        for price in prices[1:]:
            if buy >= price:
                buy = price
            else:
                answer = max(answer, price - buy)

        return answer
