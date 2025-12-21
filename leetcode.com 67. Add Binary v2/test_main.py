from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test_add_binary(self):
        sln = Solution()
        self.assertEqual(
            '100', sln.addBinary(a = "11", b = "1")
        )
