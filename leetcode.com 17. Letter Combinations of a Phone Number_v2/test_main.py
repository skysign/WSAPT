from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_letter_combinations(self):
        sol = Solution()
        self.assertEqual(["ad","ae","af","bd","be","bf","cd","ce","cf"], sol.letterCombinations('23'))
