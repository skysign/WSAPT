from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_permute(self):
        sol = Solution()
        self.assertEqual(
            [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]],
            sol.permute([1,2,3])
        )
