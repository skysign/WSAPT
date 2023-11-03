from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_character_replacement(self):
        sol = Solution()
        self.assertEqual(4, sol.characterReplacement('ABAB', 2))
