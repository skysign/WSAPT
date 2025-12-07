from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test2_convert(self):
        sln = Solution()
        self.assertEqual(
            "PINALSIGYAHRPI",
            sln.convert("PAYPALISHIRING", numRows = 4)
        )

    def test39_convert(self):
        sln = Solution()
        self.assertEqual(
            "PINALSIGYAHRPI",
            sln.convert("PAYPALISHIRING", numRows = 5)
        )
