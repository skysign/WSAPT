from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_summary_ranges(self):
        sln = Solution()
        self.assertEqual(
            ["0->2", "4->5", "7"],
            sln.summaryRanges([0,1,2,4,5,7])
        )

    def test2_summary_ranges(self):
        sln = Solution()
        self.assertEqual(
            ["0","2->4","6","8->9"],
            sln.summaryRanges([0,2,3,4,6,8,9])
        )