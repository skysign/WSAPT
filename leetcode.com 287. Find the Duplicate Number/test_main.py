from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_find_duplicate(self):
        sol = Solution()
        self.assertEqual(2, sol.findDuplicate([1,3,4,2,2]))
