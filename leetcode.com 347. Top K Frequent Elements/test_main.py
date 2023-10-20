from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_top_kfrequent(self):
        sol = Solution()
        self.assertEqual([1,2], sol.topKFrequent([1,1,1,2,2,3], 2))
