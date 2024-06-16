from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_num_decodings(self):
        sol = Solution()
        self.assertEqual(2, sol.numDecodings('12'))

    def test2_num_decodings(self):
        sol = Solution()
        self.assertEqual(3, sol.numDecodings('226'))

    def test3_num_decodings(self):
        sol = Solution()
        self.assertEqual(0, sol.numDecodings('06'))
