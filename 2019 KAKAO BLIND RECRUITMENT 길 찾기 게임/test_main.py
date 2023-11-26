from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual([[7,4,6,9,1,8,5,2,3],[9,6,5,8,1,4,3,2,7]], solution([[5,3],[11,5],[13,3],[3,5],[6,1],[1,3],[8,6],[7,2],[2,2]]))

    def testx2_solution(self):
        self.assertEqual([[1],[1]], solution([[8,6]]))

    def testx3_solution(self):
        self.assertEqual([[2,1],[1,2]], solution([[5,3],[8,6]]))

    def testx3l_solution(self):                  # 1     2       3
        self.assertEqual([[3,1,2],[2,1,3]], solution([[5,3], [6,1], [8,6]]))

    def testx3_solution(self):
        self.assertEqual([[1,2],[2,1]], solution([[8,6],[10,3]]))

    def testx32_solution(self):
        self.assertEqual([[1,2],[2,1]], solution([[8,6],[9,1],[10,3]]))
