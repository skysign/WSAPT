from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_find_peak_element(self):
        sln = Solution()
        self.assertEqual(2, sln.findPeakElement([1, 2, 3, 1]))

    def test2_find_peak_element(self):
        sln = Solution()
        self.assertEqual(5, sln.findPeakElement([1, 2, 1, 3, 5, 6, 4]))
