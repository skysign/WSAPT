from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_letter_case_permutation(self):
        sol = Solution()
        self.assertEqual(set(["a1b2","a1B2","A1b2","A1B2"]),
                         set(sol.letterCasePermutation("a1b2")))

    def test2_letter_case_permutation(self):
        sol = Solution()
        self.assertEqual(set(["3z4","3Z4"]),
                         set(sol.letterCasePermutation("3z4")))