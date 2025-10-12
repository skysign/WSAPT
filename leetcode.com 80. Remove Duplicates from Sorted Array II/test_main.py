from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_remove_duplicates(self):
        sln = Solution()
        self.assertEqual(5, sln.removeDuplicates(nums=[1, 1, 1, 2, 2, 3]))

    def test2_remove_duplicates(self):
        sln = Solution()
        self.assertEqual(7, sln.removeDuplicates(nums=[0, 0, 1, 1, 1, 1, 2, 3, 3]))

    def test3_remove_duplicates(self):
        sln = Solution()
        self.assertEqual(2, sln.removeDuplicates([1, 1, 1]))

    def test10_remove_duplicates(self):
        sln = Solution()
        self.assertEqual(2, sln.removeDuplicates([1, 1, 1, 1]))
