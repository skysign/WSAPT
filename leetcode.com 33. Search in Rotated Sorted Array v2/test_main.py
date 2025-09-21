from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test2_search(self):
        sln = Solution()
        self.assertEqual(-1, sln.search([4, 5, 6, 7, 0, 1, 2], 3))

    def test175_search(self):
        sln = Solution()
        self.assertEqual(0, sln.search([5, 1, 3], 5))
