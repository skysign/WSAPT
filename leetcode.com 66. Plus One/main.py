from typing import List


class Solution:
    def plusOne(self, digits: List[int]) -> List[int]:
        carry = 0
        answer = []
        digits.reverse()
        digits[0] += 1

        for d in digits:
            d += carry

            if d == 10:
                carry = 1
                d = 0
            else:
                carry = 0

            answer.append(d)

        if carry > 0:
            answer.append(carry)

        answer.reverse()

        return answer