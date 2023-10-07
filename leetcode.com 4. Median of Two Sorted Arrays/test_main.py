from unittest import TestCase
from main import Solution

# leetcode.com 4. Median of Two Sorted Arrays
#
# 유튜브 문제 풀이: https://youtu.be/Ye_639SfzJE?si=Un9JlPPcfFAci0-W
#
# 파이썬 소스: https://bit.ly/3RPxmDv
#
# 문제 링크: https://bit.ly/3torpmN

class TestSolution(TestCase):
    def test1_find_median_sorted_arrays(self):
        sol = Solution()
        self.assertEqual(2, sol.findMedianSortedArrays([1,3], [2]))

    def test2_find_median_sorted_arrays(self):
        sol = Solution()
        self.assertEqual(2.50000, sol.findMedianSortedArrays([1,2], [3,4]))