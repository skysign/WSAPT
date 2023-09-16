from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_min_eating_speed(self):
        sol = Solution()
        self.assertEqual(4,
                         sol.minEatingSpeed([3,6,7,11], 8))

    def test2_min_eating_speed(self):
        sol = Solution()
        self.assertEqual(30,
                         sol.minEatingSpeed([30,11,23,4,20], 5))

    def test3_min_eating_speed(self):
        sol = Solution()
        self.assertEqual(23,
                         sol.minEatingSpeed([30,11,23,4,20], 6))

    def test9_min_eating_speed(self):
        sol = Solution()
        self.assertEqual(2,
                         sol.minEatingSpeed([312884470], 312884469))

    def testmy1_min_eating_speed(self):
        sol = Solution()
        self.assertEqual(6,
                         sol.minEatingSpeed([3,8,8,11], 8))
