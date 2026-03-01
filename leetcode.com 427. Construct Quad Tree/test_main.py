from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_construct(self):
        sln = Solution()
        rtn = sln.construct([[0,1],[1,0]])
        print(rtn)
