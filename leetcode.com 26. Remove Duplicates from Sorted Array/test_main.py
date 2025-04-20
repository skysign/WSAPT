from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_remove_duplicates(self):
        sln = Solution()
        nums = [1, 1, 2]
        self.assertEqual(2, sln.removeDuplicates(nums))
        self.assertEqual([1,2], nums)
