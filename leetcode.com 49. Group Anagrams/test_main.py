from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_group_anagrams(self):
        sol = Solution()
        self.assertEqual([["bat"],["nat","tan"],["ate","eat","tea"]],
                         sol.groupAnagrams(["eat","tea","tan","ate","nat","bat"]))
