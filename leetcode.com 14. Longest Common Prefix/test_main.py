from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test95_longest_common_prefix(self):
        sln = Solution()
        self.assertEqual("flower", sln.longestCommonPrefix(["flower","flower","flower","flower"]))
