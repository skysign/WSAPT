from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_search(self):
        sol = Solution()
        self.assertEqual(4, sol.search([4,5,6,7,0,1,2], 0))

    def test2_search(self):
        sol = Solution()
        self.assertEqual(-1, sol.search([4,5,6,7,0,1,2], 3))

    def test3_search(self):
        sol = Solution()
        self.assertEqual(-1, sol.search([1], 0))

    def test180_search(self):
        sol = Solution()
        self.assertEqual(4, sol.search([4,5,6,7,8,1,2,3], 8))

    def test182_search(self):
        sol = Solution()
        self.assertEqual(1, sol.search([5,1,2,3,4], 1))