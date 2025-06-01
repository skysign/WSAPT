from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_rob(self):
        sln = Solution()
        self.assertEqual(
            4, sln.rob([1,2,3,1])
        )

    def test2_rob(self):
        sln = Solution()
        self.assertEqual(
            12, sln.rob([2,7,9,3,1])
        )
