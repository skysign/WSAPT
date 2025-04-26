from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test340_length_of_longest_substring(self):
        sln = Solution()
        self.assertEqual(1, sln.lengthOfLongestSubstring(' '))

    def test365_length_of_longest_substring(self):
        sln = Solution()
        self.assertEqual(6, sln.lengthOfLongestSubstring('asjrgapa'))
