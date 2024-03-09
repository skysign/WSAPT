from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_partition_labels(self):
        sol = Solution()
        self.assertEqual([9,7,8], sol.partitionLabels("ababcbacadefegdehijhklij"))

    def test2_partition_labels(self):
        sol = Solution()
        self.assertEqual([10], sol.partitionLabels("eccbbbbdec"))
