from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_length_of_lis(self):
        sol = Solution()
        self.assertEqual(4, sol.lengthOfLIS([10, 9, 2, 5, 3, 7, 101, 18]))

    def test2_length_of_lis(self):
        sol = Solution()
        self.assertEqual(4, sol.lengthOfLIS([0, 1, 0, 3, 2, 3]))

    def test3_length_of_lis(self):
        sol = Solution()
        self.assertEqual(1, sol.lengthOfLIS([7, 7, 7, 7, 7, 7, 7]))
