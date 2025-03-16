from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test_add_binary(self):
        sln = Solution()
        self.assertEqual(sln.addBinary('11', '1'), '100')

    def test_add_binary2(self):
        sln = Solution()
        self.assertEqual(sln.addBinary('1010', '1011'), '10101')
