from collections import Counter
from typing import List

class Solution:
    def isNStraightHand(self, hand: List[int], groupSize: int) -> bool:
        length = len(hand)

        if 0 != length % groupSize:
            return False

        hand.sort()
        hands = Counter(hand)
        keys = list(hands.keys())
        keys.sort()

        while keys:
            length = len(keys)
            if length < groupSize:
                return False

            removes = []
            t = keys[0]
            hands[t] -= 1

            if hands[t] == 0:
                removes.append(t)

            for i in range(1, groupSize):
                if i < length and t +1 == keys[i]:
                    t = keys[i]
                    hands[t] -= 1
                    if hands[t] == 0:
                        removes.append(t)
                else:
                    return False

            for i in removes:
                del hands[i]

            removes.clear()
            keys = list(hands.keys())
            keys.sort()

        return True