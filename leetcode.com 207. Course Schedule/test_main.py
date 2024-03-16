from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_can_finish(self):
        sol = Solution()
        self.assertEqual(True, sol.canFinish(2, [[1, 0]]))

    def test2_can_finish(self):
        sol = Solution()
        self.assertEqual(False, sol.canFinish(2, [[1, 0], [0, 1]]))

    def test7_can_finish(self):
        sol = Solution()
        self.assertEqual(False, sol.canFinish(20, [[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]))

    def test45_can_finish(self):
        sol = Solution()
        self.assertEqual(True, sol.canFinish(3, [[0,1],[0,2],[1,2]]))
