from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_check_inclusion(self):
        sol = Solution()
        self.assertEqual(True, sol.checkInclusion('ab', 'eidbaooo'))

    def test2_check_inclusion(self):
        sol = Solution()
        self.assertEqual(False, sol.checkInclusion('ab', 'eidboaoo'))

    def test63_check_inclusion(self):
        sol = Solution()
        self.assertEqual(True, sol.checkInclusion('a', 'ab'))

    def test97_check_inclusion(self):
        sol = Solution()
        self.assertEqual(True, sol.checkInclusion('adc', 'dcda'))

    def test104_check_inclusion(self):
        sol = Solution()
        self.assertEqual(False, sol.checkInclusion('hello', 'ooolleoooleh'))
