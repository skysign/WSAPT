from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_can_partition(self):
        solution = Solution()
        self.assertEqual(
            True,
            solution.canPartition([1, 5, 11, 5])
        )

    def test2_can_partition(self):
        solution = Solution()
        self.assertEqual(
            False,
            solution.canPartition([1,2,3,5])
        )

    def test60_can_partition(self):
        solution = Solution()
        self.assertEqual(
            True,
            solution.canPartition([1, 1])
        )

    def test64_can_partition(self):
        solution = Solution()
        self.assertEqual(
            True,
            solution.canPartition([3,3,3,4,5])
        )

    def test75_can_partition(self):
        solution = Solution()
        self.assertEqual(
            True,
            solution.canPartition([2,2,1,1])
        )

    def test110_can_partition(self):
        solution = Solution()
        self.assertEqual(
            True,
            solution.canPartition([14,9,8,4,3,2])
        )
