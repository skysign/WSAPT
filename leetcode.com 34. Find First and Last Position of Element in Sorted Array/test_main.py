from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_search_range(self):
        sln = Solution()
        self.assertEqual([3, 4], sln.searchRange([5, 7, 7, 8, 8, 10], 8))

    def test2_search_range(self):
        sln = Solution()
        self.assertEqual([-1, -1], sln.searchRange(nums=[5, 7, 7, 8, 8, 10], target=6))

    def test3_search_range(self):
        sln = Solution()
        self.assertEqual([-1, -1], sln.searchRange(nums=[], target=0))

    def test4_search_range(self):
        sln = Solution()
        self.assertEqual([0, 0], sln.searchRange(nums=[1], target=1))

    def test81_search_range(self):
        sln = Solution()
        self.assertEqual([1, 1], sln.searchRange(nums=[1, 4], target=4))
