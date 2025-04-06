from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_hamming_weight(self):
        sln = Solution()
        self.assertEqual(3, sln.hammingWeight(11))

    def test2_hamming_weight(self):
        sln = Solution()
        self.assertEqual(1, sln.hammingWeight(128))

    def test3_hamming_weight(self):
        sln = Solution()
        self.assertEqual(30, sln.hammingWeight(2147483645))
