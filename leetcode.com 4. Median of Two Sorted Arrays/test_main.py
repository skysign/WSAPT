from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_find_median_sorted_arrays(self):
        sol = Solution()
        self.assertEqual(2, sol.findMedianSortedArrays([1,3], [2]))

    def test2_find_median_sorted_arrays(self):
        sol = Solution()
        self.assertEqual(2.50000, sol.findMedianSortedArrays([1,2], [3,4]))