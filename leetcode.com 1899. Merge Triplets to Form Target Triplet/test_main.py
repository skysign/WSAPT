from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_merge_triplets(self):
        sol = Solution()
        self.assertEqual(True, sol.mergeTriplets([[2,5,3],[1,8,4],[1,7,5]], [2,7,5]))

    def test2_merge_triplets(self):
        sol = Solution()
        self.assertEqual(False, sol.mergeTriplets([[3,4,5],[4,5,6]], [3,2,5]))

    def test3_merge_triplets(self):
        sol = Solution()
        self.assertEqual(True, sol.mergeTriplets([[2,5,3],[2,3,4],[1,2,5],[5,2,3]], [5,5,5]))
