from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_letter_combinations(self):
        sol = Solution()
        self.assertEqual(["ad","ae","af","bd","be","bf","cd","ce","cf"],
                         sol.letterCombinations('23'))

    def test2_letter_combinations(self):
        sol = Solution()
        self.assertEqual([],
                         sol.letterCombinations(''))

    def test3_letter_combinations(self):
        sol = Solution()
        self.assertEqual(["a","b","c"],
                         sol.letterCombinations('2'))