from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_permute(self):
        sln = Solution()
        self.assertEqual(
            [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]],
            sln.permute(nums = [1,2,3])
        )
