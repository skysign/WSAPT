from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_roman_to_int(self):
        sln = Solution()
        self.assertEqual(3, sln.romanToInt('III'))

    def test2_roman_to_int(self):
        sln = Solution()
        self.assertEqual(58, sln.romanToInt('LVIII'))

    def test3_roman_to_int(self):
        sln = Solution()
        self.assertEqual(1994, sln.romanToInt('MCMXCIV'))