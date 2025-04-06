from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_find_kth_largest(self):
        sln = Solution()
        self.assertEqual(5, sln.findKthLargest([3, 2, 1, 5, 6, 4], 2))

    def test2_find_kth_largest(self):
        sln = Solution()
        self.assertEqual(4, sln.findKthLargest([3, 2, 3, 1, 2, 4, 5, 5, 6], 4))

    def test5_find_kth_largest(self):
        sln = Solution()
        self.assertEqual(99, sln.findKthLargest([99, 99], 1))