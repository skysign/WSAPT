from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test26_unique_paths_with_obstacles(self):
        sln = Solution()
        self.assertEqual(7, sln.uniquePathsWithObstacles(
            [[0, 0, 0, 0], [0, 1, 0, 0], [0, 0, 0, 0], [0, 0, 1, 0], [0, 0, 0, 0]]
        ))
