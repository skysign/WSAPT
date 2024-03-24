from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_is_nstraight_hand(self):
        sol = Solution()
        self.assertEqual(True, sol.isNStraightHand([1,2,3,6,2,3,4,7,8], 3))

    def test2_is_nstraight_hand(self):
        sol = Solution()
        self.assertEqual(False, sol.isNStraightHand([1,2,3,4,5], 4))

    def test44_is_nstraight_hand(self):
        sol = Solution()
        self.assertEqual(False, sol.isNStraightHand([8,10,12], 3))
