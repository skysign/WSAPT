from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_can_partition(self):
        sol = Solution()
        self.assertEqual(True, sol.canPartition([1, 5, 11, 5]))

    def test2_can_partition(self):
        sol = Solution()
        self.assertEqual(False, sol.canPartition([1, 2, 3, 5]))

    def test64_can_partition(self):
        sol = Solution()
        self.assertEqual(True, sol.canPartition([3, 3, 3, 4, 5]))

    def testx_can_partition(self):
        sol = Solution()
        self.assertEqual(False, sol.canPartition([1, 2, 5]))
