from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_partition(self):
        sol = Solution()
        self.assertEqual([["a","a","b"],["aa","b"]],
                         sol.partition('aab'))

    def test2_partition(self):
        sol = Solution()
        self.assertEqual([["a"]],
                         sol.partition('a'))

    def test8_partition(self):
        sol = Solution()
        self.assertEqual([["c","d","d"],["c","dd"]],
                         sol.partition('cdd'))
# Example 2:
#
# Input: s = "a"
# Output: [["a"]]
