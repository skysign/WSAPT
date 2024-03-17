from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_num_islands(self):
        sol = Solution()
        self.assertEqual(1, sol.numIslands([
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]))
