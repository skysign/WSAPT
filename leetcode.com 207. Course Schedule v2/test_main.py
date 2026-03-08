from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_can_finish(self):
        sln = Solution()
        self.assertEqual(True, sln.canFinish(numCourses = 2, prerequisites = [[1,0]]))
