from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_insert(self):
        sol = Solution()
        self.assertEqual([[1,5],[6,9]],\
                         sol.insert([[1,3],[6,9]], [2,5]))

    def test2_insert(self):
        sol = Solution()
        self.assertEqual([[1,2],[3,10],[12,16]],\
                         sol.insert([[1,2],[3,5],[6,7],[8,10],[12,16]], [4,8]))
