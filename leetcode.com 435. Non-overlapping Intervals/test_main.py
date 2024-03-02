from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_erase_overlap_intervals(self):
        sol = Solution()
        self.assertEqual(1, sol.eraseOverlapIntervals([[1,2],[2,3],[3,4],[1,3]]))

    def test2_erase_overlap_intervals(self):
        sol = Solution()
        self.assertEqual(2, sol.eraseOverlapIntervals([[1,2],[1,2],[1,2]]))

    def test3_erase_overlap_intervals(self):
        sol = Solution()
        self.assertEqual(0, sol.eraseOverlapIntervals([[1,2],[2,3]]))

    def test12_erase_overlap_intervals(self):
        sol = Solution()
        self.assertEqual(2, sol.eraseOverlapIntervals([[1,100],[11,22],[1,11],[2,12]]))