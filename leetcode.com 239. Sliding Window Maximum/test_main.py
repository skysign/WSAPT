from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_max_sliding_window(self):
        sol = Solution()
        self.assertEqual([3,3,5,5,6,7], sol.maxSlidingWindow([1,3,-1,-3,5,3,6,7], 3))

    def test2_max_sliding_window(self):
        sol = Solution()
        self.assertEqual([1], sol.maxSlidingWindow([1], 1))
