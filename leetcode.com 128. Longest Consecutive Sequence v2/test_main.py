from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test3_longest_consecutive(self):
        sln = Solution()
        self.assertEqual(3, sln.longestConsecutive([1, 0, 1, 2]))

    def test4_longest_consecutive(self):
        sln = Solution()
        self.assertEqual(3, sln.longestConsecutive([1, 0, 1, 2]))
